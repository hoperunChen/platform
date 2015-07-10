package com.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.config.Config;
import com.constants.ConfigConstants;
import com.context.PageContext;
import com.orm.dao.OrmDao;
import com.orm.entity.OrmEntity;
import com.util.CheckUtil;
import com.util.ElUtilEx;
/**
 * 请求处理类--dao
 * 所有的web请求都在此类中进行实例化处理
 * @author cr
 */
public class DaoWebAction extends WebAction{
	private OrmDao ormDao;
	
	public OrmDao getOrmDao() {
		return ormDao;
	}

	public void setOrmDao(OrmDao ormDao) {
		this.ormDao = ormDao;
	}
	
	/**
	 * 添加实体功能
	 * @param objectName
	 * @param request
	 * @return
	 */
	protected PageContext addEntity(String objectName, HttpServletRequest request) {

		// 装载实体(自动生成sid)
		Map<String, String[]> requestParams = request.getParameterMap();
		/* 这里实际上不用再"ormEntity = ",因为在loadEntity方法中仅仅是修改了实体属性并没有重新指向 */
		loadEntity(requestParams,true);

		// 实例化实体
		ormDao.add(getOrmEntity());

		String pageUrl = getStdPageUrl(PageTypeConst.PAGE_TYPE_VIEW);
		PageContext pc = new PageContext();
		pc.setEntity(getOrmEntity());
		pc.setPageUrl(pageUrl);
		return pc;
	}

	/**
	 * 修改实体功能
	 * @param objectName
	 * @param request
	 * @return
	 */
	protected PageContext updateEntity(String objectName,
			HttpServletRequest request) {
		String objId = request.getParameter(PARAM_OBJ_SID);
		Checker.checkSid(objId);
		// 装载实体(不自动生成sid)
		Map<String, String[]> requestParams = request.getParameterMap();
		/* 这里实际上不用再"ormEntity = ",因为在loadEntity方法中仅仅是修改了实体属性并没有重新指向 */
		loadEntity(requestParams, false);

		setOrmEntityId(objId);
		//TODO 应该先判断实体是否存在!!!
		ormDao.update(getOrmEntity());

		String pageUrl = getStdPageUrl(PageTypeConst.PAGE_TYPE_VIEW);
		PageContext pc = new PageContext();
		pc.setPageUrl(pageUrl);
		pc.setEntity(getOrmEntity());
		return pc;
	}

	/**
	 * 删除实体功能
	 * @param objectName
	 * @param request
	 * @return
	 */
	protected PageContext removeEntity(String objectName,
			HttpServletRequest request) {
		String objId = request.getParameter(PARAM_OBJ_SID);
		Checker.checkSid(objId);

		setOrmEntityId(objId);
		ormDao.remove(getOrmEntity());
		String pageUrl = getStdPageUrl(PageTypeConst.PAGE_TYPE_LIST);
		PageContext pc = new PageContext();
		pc.setPageUrl(pageUrl);
		return pc;
	}

	/**
	 * 查看实体明细功能
	 * @param objectName
	 * @param request
	 * @return
	 */
	protected PageContext viewEntity(String objectName, HttpServletRequest request) {
		String objId = request.getParameter(PARAM_OBJ_SID);
		Checker.checkSid(objId);

		OrmEntity entity = ormDao.load(getOrmEntity(), objId);
		String pageUrl = getStdPageUrl(PageTypeConst.PAGE_TYPE_VIEW);

		PageContext pc = new PageContext();
		pc.setEntity(entity);
		pc.setPageUrl(pageUrl);

		return pc;
	}
	
	/**
	 * 查询实体列表功能
	 * @param objectName
	 * @param request
	 * @return
	 */
	protected PageContext queryEntities(String objectName,
			HttpServletRequest request) {
		String pageNum = (String) ElUtilEx.nvl(request.getParameter("pageNum"),
				"1");
		String pageSize = (String) ElUtilEx.nvl(
				request.getParameter("pageSize"),
				Config.get(ConfigConstants.STD_PAGE_SIZE));

		List<OrmEntity> datas = ormDao.pagingQuery(getOrmEntity(),
				Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		int recordsCount = ormDao.getRecordsCount(getOrmEntity());
		
		
		PageContext pc = new PageContext();
		String pageUrl = getStdPageUrl(PageTypeConst.PAGE_TYPE_LIST);
		pc.setPageDatas(datas);
		pc.setRecordsCount(recordsCount);
		pc.setPageUrl(pageUrl);

		return pc;
	}

	/**
	 * 登录
	 * @param userName
	 * @param userPass
	 * @return
	 */
	protected PageContext login(String userName, String userPass) {
		PageContext pc = new PageContext();
		CheckUtil.checkNull(userName, userPass);
		String hql = "select u from UserInfo u where u.userName = :userName and u.userPass = :userPass";
		String[] params = { "userName", "userPass" };
		String[] values = { userName, userPass };
		List<OrmEntity> users = ormDao.findByHql(hql, params, values);
		if (users == null || users.isEmpty())
			return pc;
		OrmEntity user = users.get(0);
		pc.setEntity(user);
		return pc;
	}
}

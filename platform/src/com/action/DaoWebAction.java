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
 * ��������--dao
 * ���е�web�����ڴ����н���ʵ��������
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
	 * ���ʵ�幦��
	 * @param objectName
	 * @param request
	 * @return
	 */
	protected PageContext addEntity(String objectName, HttpServletRequest request) {

		// װ��ʵ��(�Զ�����sid)
		Map<String, String[]> requestParams = request.getParameterMap();
		/* ����ʵ���ϲ�����"ormEntity = ",��Ϊ��loadEntity�����н������޸���ʵ�����Բ�û������ָ�� */
		loadEntity(requestParams,true);

		// ʵ����ʵ��
		ormDao.add(getOrmEntity());

		String pageUrl = getStdPageUrl(PageTypeConst.PAGE_TYPE_VIEW);
		PageContext pc = new PageContext();
		pc.setEntity(getOrmEntity());
		pc.setPageUrl(pageUrl);
		return pc;
	}

	/**
	 * �޸�ʵ�幦��
	 * @param objectName
	 * @param request
	 * @return
	 */
	protected PageContext updateEntity(String objectName,
			HttpServletRequest request) {
		String objId = request.getParameter(PARAM_OBJ_SID);
		Checker.checkSid(objId);
		// װ��ʵ��(���Զ�����sid)
		Map<String, String[]> requestParams = request.getParameterMap();
		/* ����ʵ���ϲ�����"ormEntity = ",��Ϊ��loadEntity�����н������޸���ʵ�����Բ�û������ָ�� */
		loadEntity(requestParams, false);

		setOrmEntityId(objId);
		//TODO Ӧ�����ж�ʵ���Ƿ����!!!
		ormDao.update(getOrmEntity());

		String pageUrl = getStdPageUrl(PageTypeConst.PAGE_TYPE_VIEW);
		PageContext pc = new PageContext();
		pc.setPageUrl(pageUrl);
		pc.setEntity(getOrmEntity());
		return pc;
	}

	/**
	 * ɾ��ʵ�幦��
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
	 * �鿴ʵ����ϸ����
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
	 * ��ѯʵ���б���
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
	 * ��¼
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

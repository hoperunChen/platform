package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.context.PageContext;
import com.loader.BeanLoader;
/**
 * 请求处理类--bizflow
 * 所有的web请求都又本类进行接收
 * 并在此类中进行业务逻辑处理
 * @author cr
 */
public class BizFlowWebAction extends DaoWebAction{
	/**
	 * 请求统一入口,所有请求都由此接口进入并进行分发
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	public PageContext webAction(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		String objectName = this.getObjectName(request);
		String objectEvent = request.getParameter(PARAM_OBJ_EVENT);
		Checker.checkBaseParams(objectName, objectEvent);
		// context
		PageContext pc = null;
		if (objectEvent.trim().equals("Login"))
			pc = login(request.getParameter("userName"),
					request.getParameter("userPass"));
		else if (objectEvent.trim().equals("Query"))
			pc = queryEntities(objectName, request);
		else if (objectEvent.trim().equals("View"))
			pc = viewEntity(objectName, request);
		else if (objectEvent.trim().equals("Update"))
			pc = updateEntity(objectName, request);
		else if (objectEvent.trim().equals("Remove"))
			pc = removeEntity(objectName, request);
		else if (objectEvent.trim().equals("Add"))
			pc = addEntity(objectName, request);

		pc.setContextPath(request.getContextPath());
		request.setAttribute("pager", pc);
		return pc;
	}
	
	public static BizFlowWebAction getAction(String objectName){
		BizFlowWebAction action;
		if(objectName == null)
			action = (BizFlowWebAction) BeanLoader.get("bizFlowWebAction");
		else{
			String actionName = objectName.concat("WebAction");
			action = (BizFlowWebAction) BeanLoader.get(actionName);
			if(action == null)
				throw new RuntimeException("action bean not fonud: ["+actionName+"]");
		}
		return action;
	}
	
	public static BizFlowWebAction getAction(HttpServletRequest request){
		String objectName = request.getParameter("objectName");
		return getAction(objectName);
	}
	

}

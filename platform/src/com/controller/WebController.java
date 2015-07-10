package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.action.BizFlowWebAction;
import com.context.PageContext;
import com.log.Log;
import com.orm.entity.OrmEntity;

/**
 * ¿ØÖÆÆ÷
 * 
 * @author cr
 * 
 */
@Controller
public class WebController {
//	 private BizFlowWebAction bizAction;
//	 public WebAction getBiz() {
//	 return bizAction;
//	 }
//	
//	 @Resource(name = "bizFlowWebAction")
//	 public void setBiz(BizFlowWebAction action) {
//	 this.bizAction = action;
//	 }
	
	private BizFlowWebAction getAction(HttpServletRequest request){
		return BizFlowWebAction.getAction(request);
	}


	@RequestMapping("/list.do")
	public String doList(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		PageContext context = null;
		try {
			context = getAction(request).webAction(session,
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return context.getPageUrl();
	}

	@RequestMapping("/view.do")
	public String doView(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		PageContext context = null;
		try {
			context = getAction(request).webAction(session,
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return context.getPageUrl();
	}

	@RequestMapping("/remove.do")
	public ModelAndView doRemove(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		PageContext context = getAction(request).webAction(session, request, response);
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", "message");
		mav.setViewName(context.getPageUrl());
		return mav;
	}

	@RequestMapping("/add.do")
	public ModelAndView doAdd(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		PageContext context = getAction(request).webAction(session, request, response);
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", "message");
		mav.setViewName(context.getPageUrl());
		return mav;
	}

	@RequestMapping("/update.do")
	public ModelAndView doUpdate(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		PageContext context = getAction(request).webAction(session, request, response);
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", "message");
		mav.setViewName(context.getPageUrl());
		return mav;
	}

	@RequestMapping("/login.do")
	public ModelAndView doLogin(HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		String msg = "false";

		PageContext context = null;
		try {
			context = getAction(request).webAction(session, request, response);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		OrmEntity user = (OrmEntity) context.getEntity();
		Log.printLog(user);
		if (user != null) {
			session.setAttribute("userContext", user);
			msg = "true";
		}
		response.setContentType("text/Xml;charset=gbk");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(msg);

		} catch (IOException ex1) {
			ex1.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}
}

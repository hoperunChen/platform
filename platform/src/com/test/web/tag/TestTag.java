package com.test.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.log.Log;

public class TestTag extends TagSupport{
	private static final long serialVersionUID = 4937100762898354571L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.print("hello"+name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		Log.printLog("doSTartTag");
		return super.doStartTag();
	}
	
	
	
}

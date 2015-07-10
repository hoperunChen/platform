package com.pdmall.generate;


/**
 * 解析整个pdm最后生成多个文件
 * @author cr
 *
 */
public abstract class multiFileGenerater extends Generater{
	@Override
	protected boolean isMulti() {
		return true;
	}
}

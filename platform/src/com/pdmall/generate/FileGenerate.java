package com.pdmall.generate;



/**
 * 解析整个pdm最终生成一个文件
 * @author cr
 *
 */
public abstract class FileGenerate extends Generater{
	
	@Override
	protected boolean isMulti() {
		return false;
	}

}

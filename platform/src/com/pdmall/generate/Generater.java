package com.pdmall.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.log.Log;
import com.pdmall.build.PdmEntityBuilder;
import com.pdmall.config.PdmConfig;
import com.pdmall.entities.PdmColumn;
import com.pdmall.entities.PdmTable;
import com.pdmall.generate.constants.GenerateConst;
import com.util.CheckUtil;
import com.util.FileUtilEx;
import com.util.StringUtilEx;

/**
 * 解析pdm并且生成文件.
 * @author cr
 *
 */
public abstract class Generater implements GenerateConst{
	
	public void generate() {
		Log.printLog("begin Generate");
		if(pdmTables==null){
			PdmEntityBuilder p = new PdmEntityBuilder();
			//TODO 此处应有cache否则生成不同类型文件时每次都要重新parse
			pdmTables = p.parse();
		}
		boolean ism = isMulti();
		FileWriter fw = null ;
		try {
			Iterator<PdmTable> it = pdmTables.iterator();
			if(ism){
				while (it.hasNext()) {
					PdmTable pdmTable = it.next();
					File file = createFile(pdmTable);
					if(isOverride()){
						fw = new FileWriter(file);
						String objName = getObjName(pdmTable);
						Log.printLog("begin generateHead["+objName+"] in file["+file.getPath()+"]");
						generateFileHead(fw,pdmTable);
						Log.printLog("begin generateContent["+objName+"] in file["+file.getPath()+"]");
						generateContent(fw, pdmTable,objName);
						Log.printLog("begin generateFoot["+objName+"] in file["+file.getPath()+"]");
						generateFileFoot(fw, pdmTable);
						fw.flush();
						fw.close();
					}
					extGenerateFile(pdmTable);
				}
			}else{
				File file = createFile(null);
				if(isOverride()){
					fw = new FileWriter(file);
					Log.printLog("begin generateHead in file["+file.getPath()+"]");
					generateFileHead(fw,null);
					Log.printLog("begin generateContent in file["+file.getPath()+"]");
					while (it.hasNext()) {
						PdmTable pdmTable = it.next();
						String objName = getObjName(pdmTable);
						generateContent(fw, pdmTable,objName);
					}
					Log.printLog("begin generateFoot in file["+file.getPath()+"]");
					generateFileFoot(fw, null);
					fw.flush();
					fw.close();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.printLog("end Generate");
	}
	
	protected File createFile(PdmTable pdmTable){
		String filePath = getFilePath(pdmTable);
		String fileName = getFileName(pdmTable);
		CheckUtil.checkNull(filePath,fileName);
		File file = null;
		try {
			file = FileUtilEx.createFile(filePath , fileName,isOverride());
			Log.printLog("create file : ["+filePath+fileName+"]");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  file;
	}
	
	protected boolean isOverride(){
		boolean b = Boolean.parseBoolean(PdmConfig.get("pdm.override"));
		return b;
	}
	
	protected void genSpace(FileWriter fw,int num){
		try {
			for(int i = 0 ; i < num ; i++)
				fw.write("\t");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void genEnter(FileWriter fw,int num){
		for(int i = 0 ; i < num ; i++)
			try {
				fw.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	protected String getColDataType(PdmColumn col){
		String dataType = col.getDataType();
		if(dataType==null && col.getRefTable()!= null){
			return GenerateConst.GENERATE_JAVA_CODE_SET.trim()+"<"+getPackageFromPdmTable(col.getRefTable())+"."+getObjName(col.getRefTable())+"> ";
		}else if(col.getRefTable()!= null)
			return getPackageFromPdmTable(col.getRefTable())+"."+getObjName(col.getRefTable())+" ";
		else if(dataType.toLowerCase().contains("int"))
			return GenerateConst.GENERATE_JAVA_CODE_INTEGER;
		else if(dataType.toLowerCase().contains("varchar"))
			return GenerateConst.GENERATE_JAVA_CODE_STRING;
		else if(dataType.toLowerCase().contains("float"))
			return GenerateConst.GENERATE_JAVA_CODE_DOUBLE;
		else if(dataType.toLowerCase().contains("date"))
			return GenerateConst.GENERATE_JAVA_CODE_TIMESTAMP;
		return "";
	}
	
	/**
	 * 获得包路径
	 * 比如user.UserInfo的包路径是:
	 * user
	 * @param pdmTable 根据pdmTable得到路径
	 * @return String
	 */
	protected String getPackageFromPdmTable(PdmTable pdmTable){
		String rtn = pdmTable.getPdmPackage().getPhysicsPath();
		rtn = rtn.replace("/", ".");
		return rtn;
	}
	
	/**
	 * 获得对象名
	 * 如:UserInfo
	 * @param pdmTable
	 * @return String
	 */
	protected String getObjName(PdmTable pdmTable) {
		String className = StringUtilEx.convertToPascalFromDb(pdmTable.getTableCode());
		return className;
	}
	
	//=========================abstract=============================
	
	/**
	 * 是否生成多个文件<br/>
	 * 如果生成的是单个文件,那么将在一个文件中遍历所有table,否则每个table分成一个文件
	 * @return boolean
	 */
	protected abstract boolean isMulti();
	
	/**
	 * 获得包路径
	 * 比如user.UserInfo的包路径是:
	 * user
	 * @param classPath [pdm.target_src.dir]\\[pdm.basePackage]\\user\\UserInfo.java
	 * @return String
	 */
	protected abstract String getPackageFromFilePath(String classPath);
	
	/**
	 * 获得文件名
	 * 比如java文件UserInfo的文件名是:
	 * UserInfo.java
	 * @param pdmTable
	 * @return String
	 */
	protected abstract String getFileName(PdmTable pdmTable);
	
	/**
	 * 获得文件路径
	 * 比如java文件UserInfo的路径是:
	 * [pdm.target_src.dir]/[pdm.basePackage]/user/
	 * @param pdmTable
	 * @return String
	 */
	protected abstract String getFilePath(PdmTable pdmTable);
	
	/**
	 * 生成文件内容,在multi==true的时候一个文件执行一次该方法,否则一个文件执行多次该方法
	 * @param fw 使用该对象执行写入功能
	 * @param pdmTable 根据pdmTable生成
	 * @param objName 对象名称
	 * @throws IOException
	 */
	protected abstract void generateContent(FileWriter fw,PdmTable pdmTable,String objName) throws IOException;
	
	/**
	 * 生成扩展文件.<br/>
	 * 在生成java实体类UserInfo时,生成一个UserInfo.java的同时还会生成_entity/_UserInfo.java
	 * @param pdmTable
	 */
	protected abstract void extGenerateFile(PdmTable pdmTable);
	
	/**
	 * 生成文件头,在multi==true的时候一个文件执行一次该方法,否则一个文件执行多次该方法
	 * @param fw
	 * @param pdmTable
	 */
	protected abstract void generateFileHead(FileWriter fw,PdmTable pdmTable) throws IOException ;
	
	/**
	 * 生成文件脚,在multi==true的时候一个文件执行一次该方法,否则一个文件执行多次该方法
	 * @param fw
	 * @param pdmTable
	 * @throws IOException
	 */
	protected abstract void generateFileFoot(FileWriter fw,PdmTable pdmTable) throws IOException ;
	//================================property============================
	private List<PdmTable> pdmTables = null;
	
	public List<PdmTable> getPdmTables() {
		return pdmTables;
	}

	public void setPdmTables(List<PdmTable> pdmTables) {
		this.pdmTables = pdmTables;
	}
}

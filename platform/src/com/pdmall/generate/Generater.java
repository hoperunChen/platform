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
 * ����pdm���������ļ�.
 * @author cr
 *
 */
public abstract class Generater implements GenerateConst{
	
	public void generate() {
		Log.printLog("begin Generate");
		if(pdmTables==null){
			PdmEntityBuilder p = new PdmEntityBuilder();
			//TODO �˴�Ӧ��cache�������ɲ�ͬ�����ļ�ʱÿ�ζ�Ҫ����parse
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
	 * ��ð�·��
	 * ����user.UserInfo�İ�·����:
	 * user
	 * @param pdmTable ����pdmTable�õ�·��
	 * @return String
	 */
	protected String getPackageFromPdmTable(PdmTable pdmTable){
		String rtn = pdmTable.getPdmPackage().getPhysicsPath();
		rtn = rtn.replace("/", ".");
		return rtn;
	}
	
	/**
	 * ��ö�����
	 * ��:UserInfo
	 * @param pdmTable
	 * @return String
	 */
	protected String getObjName(PdmTable pdmTable) {
		String className = StringUtilEx.convertToPascalFromDb(pdmTable.getTableCode());
		return className;
	}
	
	//=========================abstract=============================
	
	/**
	 * �Ƿ����ɶ���ļ�<br/>
	 * ������ɵ��ǵ����ļ�,��ô����һ���ļ��б�������table,����ÿ��table�ֳ�һ���ļ�
	 * @return boolean
	 */
	protected abstract boolean isMulti();
	
	/**
	 * ��ð�·��
	 * ����user.UserInfo�İ�·����:
	 * user
	 * @param classPath [pdm.target_src.dir]\\[pdm.basePackage]\\user\\UserInfo.java
	 * @return String
	 */
	protected abstract String getPackageFromFilePath(String classPath);
	
	/**
	 * ����ļ���
	 * ����java�ļ�UserInfo���ļ�����:
	 * UserInfo.java
	 * @param pdmTable
	 * @return String
	 */
	protected abstract String getFileName(PdmTable pdmTable);
	
	/**
	 * ����ļ�·��
	 * ����java�ļ�UserInfo��·����:
	 * [pdm.target_src.dir]/[pdm.basePackage]/user/
	 * @param pdmTable
	 * @return String
	 */
	protected abstract String getFilePath(PdmTable pdmTable);
	
	/**
	 * �����ļ�����,��multi==true��ʱ��һ���ļ�ִ��һ�θ÷���,����һ���ļ�ִ�ж�θ÷���
	 * @param fw ʹ�øö���ִ��д�빦��
	 * @param pdmTable ����pdmTable����
	 * @param objName ��������
	 * @throws IOException
	 */
	protected abstract void generateContent(FileWriter fw,PdmTable pdmTable,String objName) throws IOException;
	
	/**
	 * ������չ�ļ�.<br/>
	 * ������javaʵ����UserInfoʱ,����һ��UserInfo.java��ͬʱ��������_entity/_UserInfo.java
	 * @param pdmTable
	 */
	protected abstract void extGenerateFile(PdmTable pdmTable);
	
	/**
	 * �����ļ�ͷ,��multi==true��ʱ��һ���ļ�ִ��һ�θ÷���,����һ���ļ�ִ�ж�θ÷���
	 * @param fw
	 * @param pdmTable
	 */
	protected abstract void generateFileHead(FileWriter fw,PdmTable pdmTable) throws IOException ;
	
	/**
	 * �����ļ���,��multi==true��ʱ��һ���ļ�ִ��һ�θ÷���,����һ���ļ�ִ�ж�θ÷���
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

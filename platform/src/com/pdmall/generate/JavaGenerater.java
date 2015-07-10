package com.pdmall.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import com.log.Log;
import com.pdmall.config.PdmConfig;
import com.pdmall.entities.PdmColumn;
import com.pdmall.entities.PdmTable;
import com.pdmall.generate.constants.GenerateConst;
import com.util.FileUtilEx;
import com.util.StringUtilEx;

public class JavaGenerater extends multiFileGenerater {
	
	@Override
	protected String getFileName(PdmTable pdmTable) {
		String classPhyName = StringUtilEx.convertToPascalFromDb(pdmTable.getTableCode()).concat(".java");
		return classPhyName;
	}

	@Override
	protected String getFilePath(PdmTable pdmTable) {
		String path = PdmConfig.get("pdm.target_src.dir")
				.concat(PdmConfig.get("pdm.basePackage")+"/")
				.concat("src/")
				.concat(pdmTable.getPdmPackage().getPhysicsPath())
				.concat("/");
		return path;
	}
	
	@Override
	protected void generateContent(FileWriter fw,PdmTable pdmTable, String objName) throws IOException {
		fw.write(GenerateConst.GENERATE_JAVA_CODE_PACKAGE+getPackageFromPdmTable(pdmTable)+";\n");
		fw.write("import "+getPackageFromPdmTable(pdmTable)+"._entity._"+objName+";\n");
		fw.write(GenerateConst.GENERATE_JAVA_CODE_PUBLIC+GenerateConst.GENERATE_JAVA_CODE_CLASS+objName+" "+GenerateConst.GENERATE_JAVA_CODE_EXTENDS+"_"+objName+"{\n");
		fw.write(getSerialId());
		fw.write("}");
	}
	/**
	 * Éú³ÉPOJO
	 * @param pdmTable
	 * 
	 */
	@Override
	protected void extGenerateFile(PdmTable pdmTable) {
		try {
			String pojoClassPath = getFilePath(pdmTable)+"_entity/";
			String pojoClassPhyName = "_"+getFileName(pdmTable);
			String className = getObjName(pdmTable);
			File classFile = FileUtilEx.createFile(pojoClassPath , pojoClassPhyName);
			Log.printLog("generate PojoJava : ["+pojoClassPath+pojoClassPhyName+"]");
			FileWriter fw = new FileWriter(classFile);
			fw.write(GenerateConst.GENERATE_JAVA_CODE_PACKAGE+getPackageFromFilePath(pojoClassPath)+";\n");
			fw.write("import com.orm.entity.OrmEntity;\n");
			fw.write(GenerateConst.GENERATE_JAVA_CODE_PUBLIC+GenerateConst.GENERATE_JAVA_CODE_CLASS+"_"+className+" ");
			fw.write(GenerateConst.GENERATE_JAVA_CODE_EXTENDS+"OrmEntity {\n");
			fw.write(getSerialId());
			fw.write(getPropertyCode(pdmTable));
			fw.write(getSetterGetter(pdmTable));
			fw.write("}");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getPropertyCode(PdmTable pdmTable){
		StringBuffer rtn = new StringBuffer();
		rtn.append("\t//FIELDS\n");
		Iterator<PdmColumn> cols = pdmTable.getColumns().iterator();
		while(cols.hasNext()){
			PdmColumn col = cols.next();
			rtn.append("\t");
			rtn.append(GenerateConst.GENERATE_JAVA_CODE_PRIVATE);
			rtn.append(getColDataType(col));
			rtn.append(getColName(col));
			rtn.append(";");
			rtn.append("\t//"+col.getName()+"\n");
		}
		return rtn.toString();
		
	}
	
	private String getSetterGetter(PdmTable pdmTable){
		StringBuffer rtn = new StringBuffer();
		rtn.append("\t//GETTER&SETTER\n");
		Iterator<PdmColumn> cols = pdmTable.getColumns().iterator();
		while(cols.hasNext()){
			PdmColumn col = cols.next();
			rtn.append("\t"+GenerateConst.GENERATE_JAVA_CODE_PUBLIC+GenerateConst.GENERATE_JAVA_CODE_VOID);
			rtn.append("set"+StringUtilEx.convertToPascalFromCamel(getColName(col))+"("+getColDataType(col)+" "+getColName(col)+"){\n");
			rtn.append("\t\tthis."+getColName(col)+" = "+getColName(col)+";\n");
			rtn.append("\t}\n");
			
			rtn.append("\t"+GenerateConst.GENERATE_JAVA_CODE_PUBLIC+getColDataType(col)+"");
			rtn.append("get"+StringUtilEx.convertToPascalFromCamel(getColName(col))+"(){\n");
			rtn.append("\t\treturn "+getColName(col)+";\n");
			rtn.append("\t}\n");
		}
		return rtn.toString();
	}
	
	
	public String getColName(PdmColumn col){
		if(col.getRefName()!=null)
			return col.getRefName();
		else if(col.getRefTable()!=null){
			String colName = StringUtilEx.convertToCamelFromDb(col.getRefTable().getTableCode());
			if(col.isParent())
				return colName+"s";
			else
				return colName;
		}else
			return StringUtilEx.convertToCamelFromDb(col.getCode());
	}
	
	protected String getPackageFromFilePath(String classPath){
		String rtn = classPath.substring(classPath.indexOf("src/")+4,classPath.lastIndexOf("/"));
		rtn = rtn.replace("/", ".");
		return rtn; 
	}
	
	
	
	private String getSerialId(){
		return "\tprivate static final long serialVersionUID = 1L;\n";
	}
	
	//=============useless======================

	@Override
	protected void generateFileHead(FileWriter fw, PdmTable pdmTable)
			throws IOException {
	}

	@Override
	protected void generateFileFoot(FileWriter fw, PdmTable pdmTable)
			throws IOException {
		
	}


}

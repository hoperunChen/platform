package com.pdmall.generate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import com.pdmall.config.PdmConfig;
import com.pdmall.entities.PdmColumn;
import com.pdmall.entities.PdmTable;
import com.pdmall.generate.constants.GenerateConst;
import com.util.ElUtilEx;
import com.util.StringUtilEx;

public class HbmGenerater extends multiFileGenerater {

	@Override
	protected String getFileName(PdmTable pdmTable) {
		String classPhyName = StringUtilEx.convertToPascalFromDb(
				pdmTable.getTableCode()).concat(".hbm.xml");
		return classPhyName;
	}

	@Override
	protected String getFilePath(PdmTable pdmTable) {
		String path = PdmConfig
				.get("pdm.target_web.dir")
				.concat(ElUtilEx.iif(
						PdmConfig.get("pdm.web_use_base_package")
								.equals("true"),
						"/" + PdmConfig.get("pdm.basePackage"), "").toString())
				.concat("/hbm/")
				.concat(pdmTable.getPdmPackage().getPhysicsPath()).concat("/");
		return path;
	}

	@Override
	protected void generateContent(FileWriter fw, PdmTable pdmTable, String objName)
			throws IOException {
		fw.write("<hibernate-mapping>\n");
		fw.write("\t<class " + GenerateConst.GENERATE_XML_CODE_NAME + "= \""
				+ getPackageFromPdmTable(pdmTable) + "." + objName + "\" ");
		fw.write(GenerateConst.GENERATE_HBM_CODE_TABLE + "= \""
				+ pdmTable.getTableCode() + "\" ");
		fw.write(" >\n");
		// TODO fw.write(pdmTable.getDbms());
		fw.write(buildIdNode(pdmTable));
		fw.write(buildColumn(pdmTable));
		fw.write("\t</class>\n");
		fw.write("</hibernate-mapping >");
	}

	private String buildIdNode(PdmTable pdmTable) {
		PdmColumn pk = pdmTable.getPrimaryKey();
		if (pk == null)
			return null;
		StringBuffer rtn = new StringBuffer();
		rtn.append("\t\t<" + GenerateConst.GENERATE_XML_CODE_ID);
		rtn.append(GenerateConst.GENERATE_XML_CODE_NAME + " = \""
				+ StringUtilEx.convertToCamelFromDb(pk.getCode()) + "\" ");
		rtn.append(GenerateConst.GENERATE_HBM_CODE_TYPE + " = \""
				+ getColDataType(pk).trim() + "\" ");
		rtn.append(GenerateConst.GENERATE_HBM_CODE_COLUMN + " = \""
				+ pk.getCode().trim() + "\" ");
		rtn.append(" >\n");
		rtn.append("\t\t</id>\n");
		return rtn.toString();
	}

	public String buildColumn(PdmTable pdmTable) {
		StringBuffer rtn = new StringBuffer();
		Iterator<PdmColumn> cols = pdmTable.getColumns().iterator();
		while (cols.hasNext()) {
			PdmColumn col = cols.next();
			if (col.isPrimaryKey())
				continue;
			else if (col.isParent())
				rtn.append(buildSetNode(col));
			else if (col.getRefTable() != null)
				rtn.append(buildMtoNode(col));
			else
				rtn.append(buildProperty(col));
		}
		return rtn.toString();
	}

	private String buildMtoNode(PdmColumn col) {
		StringBuffer rtn = new StringBuffer();
		PdmTable refTable = col.getRefTable();
		String propName = col.getRefName() == null ? StringUtilEx
				.convertToCamelFromDb(refTable.getTableCode()) : col
				.getRefName();
		rtn.append("\t\t<many-to-one " + GenerateConst.GENERATE_XML_CODE_NAME
				+ "= \"" + propName + "\" "
				+ GenerateConst.GENERATE_HBM_CODE_CLASS + "= \""
				+ getPackageFromPdmTable(refTable) + "."
				+ StringUtilEx.convertToPascalFromDb(refTable.getTableCode())
				+ "\" " + GenerateConst.GENERATE_HBM_CODE_NOTNULL + "= \""
				+ col.isNotNull() + "\">\n");
		rtn.append("\t\t\t<column " + GenerateConst.GENERATE_XML_CODE_NAME
				+ "=\"" + col.getCode() + "\" />\n");
		rtn.append("\t\t</many-to-one>\n");
		return rtn.toString();
	}

	private String buildSetNode(PdmColumn col) {
		StringBuffer rtn = new StringBuffer();
		PdmTable refTable = col.getRefTable();
		String propName = col.getRefName() == null ? StringUtilEx
				.convertToCamelFromDb(refTable.getTableCode()) + "s" : col
				.getRefName();
		rtn.append("\t\t<set "
				+ GenerateConst.GENERATE_XML_CODE_NAME
				+ "= \""
				+ propName
				+ "\" "
				+ "inverse=\"true\" cascade=\"persist, merge,save-update, evict, replicate, lock, refresh\" >\n");
		rtn.append("\t\t\t<key>\n");
		rtn.append("\t\t\t\t<column " + GenerateConst.GENERATE_XML_CODE_NAME
				+ "= \"" + col.getRefColumn().getCode() + "\" />\n");
		rtn.append("\t\t\t</key>\n");
		rtn.append("\t\t\t<one-to-many "
				+ GenerateConst.GENERATE_HBM_CODE_CLASS + "= \""
				+ getPackageFromPdmTable(refTable) + "."
				+ StringUtilEx.convertToPascalFromDb(refTable.getTableCode())
				+ "\" />\n");
		rtn.append("\t\t</set>\n");
		return rtn.toString();
	}

	private String buildProperty(PdmColumn col) {
		StringBuffer rtn = new StringBuffer();
		rtn.append("\t\t<property " + GenerateConst.GENERATE_XML_CODE_NAME
				+ " = \"" + StringUtilEx.convertToCamelFromDb(col.getCode())
				+ "\" ");
		rtn.append(GenerateConst.GENERATE_HBM_CODE_COLUMN + "= \""
				+ col.getCode() + "\" ");
		rtn.append(GenerateConst.GENERATE_HBM_CODE_TYPE + "= \""
				+ getColDataType(col) + "\" ");
		rtn.append(GenerateConst.GENERATE_HBM_CODE_NOTNULL + "= \""
				+ col.isNotNull() + "\" ");
		rtn.append(GenerateConst.GENERATE_HBM_CODE_INSERT + "= \"true\" ");
		rtn.append(GenerateConst.GENERATE_HBM_CODE_UPDATE + "= \"true\" ");
		rtn.append(">\n");
		rtn.append("\t\t</property>\n");
		return rtn.toString();
	}

	private String getHbmHead() {
		String hbmHead = "\n<!DOCTYPE hibernate-mapping PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\" \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">\n";
		return hbmHead;
	}

	protected String getColDataType(PdmColumn col) {
		String dataType = col.getDataType();
		if (dataType == null && col.getRefTable() != null) {
			return GenerateConst.GENERATE_HBM_CODE_SET.trim() + "<"
					+ getPackageFromPdmTable(col.getRefTable())
					+ "." + getObjName(col.getRefTable()) + "> ";
		} else if (col.getRefTable() != null)
			return getPackageFromPdmTable(col.getRefTable()) + "."
					+ getObjName(col.getRefTable()) + " ";
		else if (dataType.toLowerCase().contains("int"))
			return GenerateConst.GENERATE_HBM_CODE_INTEGER;
		else if (dataType.toLowerCase().contains("varchar"))
			return GenerateConst.GENERATE_HBM_CODE_STRING;
		else if (dataType.toLowerCase().contains("float"))
			return GenerateConst.GENERATE_HBM_CODE_DOUBLE;
		else if (dataType.toLowerCase().contains("date"))
			return GenerateConst.GENERATE_HBM_CODE_TIMESTAMP;
		return "";
	}

	@Override
	protected void generateFileHead(FileWriter fw, PdmTable pdmTable)
			throws IOException {
		fw.write(GenerateConst.GENERATE_XML_CODE_XML_HEAD);
		fw.write(getHbmHead());
	}
	

	//=============useless======================

	@Override
	protected void generateFileFoot(FileWriter fw, PdmTable pdmTable)
			throws IOException {
	}

	@Override
	protected String getPackageFromFilePath(String filePath) {
		return null;
	}
	
	@Override
	protected void extGenerateFile(PdmTable pdmTable) {
	}
}

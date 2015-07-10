package com.pdmall.generate;

import java.io.FileWriter;
import java.io.IOException;
import com.pdmall.config.PdmConfig;
import com.pdmall.entities.PdmTable;
import com.util.ElUtilEx;
import com.util.StringUtilEx;

public class ActionGenerater extends FileGenerate {

	@Override
	protected String getFileName(PdmTable pdmTable) {
		String fileName = PdmConfig.get("pdm.basePackage")
				.concat(".action.xml");
		return fileName;
	}

	@Override
	protected String getFilePath(PdmTable pdmTable) {
		String path = PdmConfig.get("pdm.target_src.dir")
				+ PdmConfig.get("pdm.basePackage").concat("/src/");
		return path;
	}

	@Override
	protected void generateContent(FileWriter fw, PdmTable pdmTable,
			String objName) throws IOException {
		genSpace(fw, 1);
		fw.write("<bean " + GENERATE_XML_CODE_ID + " = \"" + objName
				+ "WebAction\" ");
		fw.write(GENERATE_SPR_CODE_PARENT + " = \"bizFlowWebAction\" "
				+ GENERATE_SPR_CODE_INIT_METHOD + "= \"init\" >\n");
		genSpace(fw, 2);
		fw.write("<" + GENERATE_SPR_CODE_PROPERTY + " "
				+ GENERATE_XML_CODE_NAME + " = \"className\" "
				+ GENERATE_SPR_CODE_VALUE + " = \"");
		fw.write(getPackageFromPdmTable(pdmTable) + "." + objName + "\" />\n");
		fw.write("<" + GENERATE_SPR_CODE_PROPERTY + " "
				+ GENERATE_XML_CODE_NAME + " = \"bizFlowDefinition\" "
				+ GENERATE_SPR_CODE_VALUE + " = \"");
		fw.write(getBizFlowDefinition(pdmTable) + "\" />\n");
		genSpace(fw, 1);
		fw.write("</bean>\n");
	}

	protected String getBizFlowDefinition(PdmTable pdmTable) {
		String path = PdmConfig
				.get("pdm.target_web.dir")
				.concat(ElUtilEx.iif(
						PdmConfig.get("pdm.web_use_base_package")
								.equals("true"),
						"/" + PdmConfig.get("pdm.basePackage"), "").toString())
				.concat("/web/")
				.concat(pdmTable.getPdmPackage().getPhysicsPath())
				.concat("/")
				.concat(StringUtilEx.convertToPascalFromDb(
						pdmTable.getTableCode()).concat(".biz.xml"));
		return path;
	}

	@Override
	protected void generateFileHead(FileWriter fw, PdmTable pdmTable)
			throws IOException {
		fw.write(GENERATE_XML_CODE_XML_HEAD);
		fw.write(GENERATE_SPR_CODE_HEAD);
		fw.write("<beans>\n");
	}

	@Override
	protected void generateFileFoot(FileWriter fw, PdmTable pdmTable)
			throws IOException {
		fw.write("</beans>\n");
	}

	private String getSpringHead() {
		String hbmHead = "\n<!DOCTYPE beans PUBLIC \"-//SPRING//DTD BEAN//EN\" \"http://www.springframework.org/dtd/spring-beans.dtd\">\n";
		return hbmHead;
	}

	// =============useless======================
	@Override
	protected void extGenerateFile(PdmTable pdmTable) {

	}

	@Override
	protected String getPackageFromFilePath(String classPath) {
		return null;
	}

}

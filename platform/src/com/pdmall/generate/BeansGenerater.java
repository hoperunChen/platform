package com.pdmall.generate;

import java.io.FileWriter;
import java.io.IOException;

import com.pdmall.config.PdmConfig;
import com.pdmall.entities.PdmTable;

public class BeansGenerater extends FileGenerate{
	@Override
	protected String getFileName(PdmTable pdmTable) {
		String fileName = PdmConfig.get("pdm.basePackage").concat(".beans.xml");
		return fileName;
	}

	@Override
	protected String getFilePath(PdmTable pdmTable) {
		String path = PdmConfig.get("pdm.target_src.dir")+PdmConfig.get("pdm.basePackage").concat("/src/");
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
	
	//=============useless======================
	
	@Override
	protected void generateContent(FileWriter fw, PdmTable pdmTable,
			String objName) throws IOException {
		
	}

	@Override
	protected void extGenerateFile(PdmTable pdmTable) {
		
	}
	
	@Override
	protected String getPackageFromFilePath(String classPath) {
		return null;
	}
}

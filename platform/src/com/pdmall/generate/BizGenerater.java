package com.pdmall.generate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pdmall.config.PdmConfig;
import com.pdmall.entities.PdmTable;
import com.pdmall.generate.constants.GenerateConst;
import com.util.ElUtilEx;
import com.util.StringUtilEx;

/**
 * 生成biz文件,写的很差,需要重构.
 * @author cr
 *
 */
public class BizGenerater extends multiFileGenerater{

	@Override
	protected String getFileName(PdmTable pdmTable) {
		String classPhyName = StringUtilEx.convertToPascalFromDb(
				pdmTable.getTableCode()).concat(".biz.xml");
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
				.concat("/web/")
				.concat(pdmTable.getPdmPackage().getPhysicsPath()).concat("/");
		return path;
	}

	@Override
	protected void generateFileHead(FileWriter fw, PdmTable pdmTable)
			throws IOException {
		fw.write(GenerateConst.GENERATE_XML_CODE_XML_HEAD);
		fw.write("\n<biz-flow>\n");
	}

	@Override
	protected void generateFileFoot(FileWriter fw, PdmTable pdmTable)
			throws IOException {
		fw.write("\n</biz-flow>\n");
	}
	
	@Override
	protected void generateContent(FileWriter fw, PdmTable pdmTable,
			String objName) throws IOException {
		fw.write(getBizs());
		fw.write(getStateMachine());
	}
	
	private String getBizs(){
		String bizsS = "\t<bizs>\n";
		bizsS = bizsS.concat("\t\t"+getBiz());
		bizsS = bizsS.concat("\n\t</bizs>");
		return bizsS;
	}
	
	/**
	 * 生成biz,这里应该改成一个内部类
	 * @return
	 */
	private String getBiz(){
		String bizS = "<biz id=\"default\" alias=\"*\">";
		bizS=bizS.concat("</biz>");
		return bizS;
	}
	
	/**
	 * 生成StateMachine,StateMachine中包含Action、...等元素(目前只需要Action)
	 * @return
	 */
	private String getStateMachine(){
		String stateMachine = "\n\t<state-machine mustTransit=\"true\">\n";
		stateMachine = stateMachine.concat(getAction());
		stateMachine = stateMachine.concat("\t</state-machine>");
		return stateMachine;
	}
	
	/**
	 * 生成action,这里应该改成一个内部类
	 * @return
	 */
	private String getAction(){
		String actionS = "";
		actionS = actionS.concat("\t\t<actions>\n");
		List<Map<String,String>> params = getActionParams();
		for (Map<String, String> param : params) {
			actionS=actionS.concat("\t\t\t<action id=\""+param.get("id")+"\" >\n");
			actionS=actionS.concat("\t\t\t</action>\n");
		}
		actionS = actionS.concat("\t\t</actions>\n");
		return actionS;
	}
	
	private List<Map<String,String>> getActionParams(){
		List<Map<String,String>> actionParams = new ArrayList<Map<String,String>>(); 
		class ParamsBuilder{
			private Map<String, String> params;
			public ParamsBuilder(){
				params = new HashMap<String,String>();
			}
			
			Map<String, String> buildParams(){
				return params; 
			}
			
			ParamsBuilder setId(String value){
				params.put("id", value);
				return this;
			}
			
		}
		actionParams.add(new ParamsBuilder().setId("Add-default").buildParams());
		actionParams.add(new ParamsBuilder().setId("Update-default").buildParams());
		actionParams.add(new ParamsBuilder().setId("Remove-default").buildParams());
		actionParams.add(new ParamsBuilder().setId("ViewDetail-default").buildParams());
		return actionParams;
	}
	
	//=============useless======================
	@Override
	protected void extGenerateFile(PdmTable pdmTable) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected String getPackageFromFilePath(String classPath) {
		// TODO Auto-generated method stub
		return null;
	}

}

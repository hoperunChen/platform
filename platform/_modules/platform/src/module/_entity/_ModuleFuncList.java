package module._entity;
import com.orm.entity.OrmEntity;
public class _ModuleFuncList extends OrmEntity {
	private static final long serialVersionUID = 1L;
	//FIELDS
	private java.lang.Integer sid;	//SID
	private java.lang.String funcName;	//���ܵ�����
	private java.lang.String funcDesc;	//���ܵ�����
	private user.UserInfo creater;	//������
	private java.sql.Timestamp createTime;	//����ʱ��
	private user.UserInfo updater;	//�޸���
	private java.sql.Timestamp updateTime;	//�޸�ʱ��
	private module.ModuleList moduleList;	//ģ��
	private java.util.Set<auth.RoleModuleAuth> roleModuleAuths;	//null
	//GETTER&SETTER
	public void setSid(java.lang.Integer  sid){
		this.sid = sid;
	}
	public java.lang.Integer getSid(){
		return sid;
	}
	public void setFuncName(java.lang.String  funcName){
		this.funcName = funcName;
	}
	public java.lang.String getFuncName(){
		return funcName;
	}
	public void setFuncDesc(java.lang.String  funcDesc){
		this.funcDesc = funcDesc;
	}
	public java.lang.String getFuncDesc(){
		return funcDesc;
	}
	public void setCreater(user.UserInfo  creater){
		this.creater = creater;
	}
	public user.UserInfo getCreater(){
		return creater;
	}
	public void setCreateTime(java.sql.Timestamp  createTime){
		this.createTime = createTime;
	}
	public java.sql.Timestamp getCreateTime(){
		return createTime;
	}
	public void setUpdater(user.UserInfo  updater){
		this.updater = updater;
	}
	public user.UserInfo getUpdater(){
		return updater;
	}
	public void setUpdateTime(java.sql.Timestamp  updateTime){
		this.updateTime = updateTime;
	}
	public java.sql.Timestamp getUpdateTime(){
		return updateTime;
	}
	public void setModuleList(module.ModuleList  moduleList){
		this.moduleList = moduleList;
	}
	public module.ModuleList getModuleList(){
		return moduleList;
	}
	public void setRoleModuleAuths(java.util.Set<auth.RoleModuleAuth>  roleModuleAuths){
		this.roleModuleAuths = roleModuleAuths;
	}
	public java.util.Set<auth.RoleModuleAuth> getRoleModuleAuths(){
		return roleModuleAuths;
	}
}
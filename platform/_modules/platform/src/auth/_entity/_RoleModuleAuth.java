package auth._entity;
import com.orm.entity.OrmEntity;
public class _RoleModuleAuth extends OrmEntity {
	private static final long serialVersionUID = 1L;
	//FIELDS
	private java.lang.Integer sid;	//SID
	private java.sql.Timestamp createTime;	//����ʱ��
	private auth.RoleInfo roleInfo;	//��ɫ
	private user.UserInfo userInfo;	//������
	private module.ModuleFuncList moduleFuncList;	//���ܵ�
	//GETTER&SETTER
	public void setSid(java.lang.Integer  sid){
		this.sid = sid;
	}
	public java.lang.Integer getSid(){
		return sid;
	}
	public void setCreateTime(java.sql.Timestamp  createTime){
		this.createTime = createTime;
	}
	public java.sql.Timestamp getCreateTime(){
		return createTime;
	}
	public void setRoleInfo(auth.RoleInfo  roleInfo){
		this.roleInfo = roleInfo;
	}
	public auth.RoleInfo getRoleInfo(){
		return roleInfo;
	}
	public void setUserInfo(user.UserInfo  userInfo){
		this.userInfo = userInfo;
	}
	public user.UserInfo getUserInfo(){
		return userInfo;
	}
	public void setModuleFuncList(module.ModuleFuncList  moduleFuncList){
		this.moduleFuncList = moduleFuncList;
	}
	public module.ModuleFuncList getModuleFuncList(){
		return moduleFuncList;
	}
}
package auth._entity;
import com.orm.entity.OrmEntity;
public class _RoleInfo extends OrmEntity {
	private static final long serialVersionUID = 1L;
	//FIELDS
	private java.lang.Integer sid;	//SID
	private java.lang.Integer id;	//��ɫID
	private java.lang.String roleName;	//��ɫ��
	private java.lang.String roleDesc;	//����
	private java.lang.String domainName;	//��ȫ��
	private java.lang.Integer roleRank;	//��ɫ�ȼ�
	private java.sql.Timestamp startTime;	//����ʱ��
	private java.sql.Timestamp expireTime;	//����ʱ��
	private user.UserInfo create;	//������
	private java.sql.Timestamp createTime;	//����ʱ��
	private user.UserInfo updater;	//�޸���
	private java.sql.Timestamp updateTime;	//�޸�ʱ��
	private java.lang.Integer roleStatus;	//��ɫ״̬
	private java.lang.Integer roleMaxRank;	//���ȼ�
	private java.util.Set<auth.RoleModuleAuth> roleModuleAuths;	//null
	private java.util.Set<auth.UserRoleMapping> userRoleMappings;	//null
	//GETTER&SETTER
	public void setSid(java.lang.Integer  sid){
		this.sid = sid;
	}
	public java.lang.Integer getSid(){
		return sid;
	}
	public void setId(java.lang.Integer  id){
		this.id = id;
	}
	public java.lang.Integer getId(){
		return id;
	}
	public void setRoleName(java.lang.String  roleName){
		this.roleName = roleName;
	}
	public java.lang.String getRoleName(){
		return roleName;
	}
	public void setRoleDesc(java.lang.String  roleDesc){
		this.roleDesc = roleDesc;
	}
	public java.lang.String getRoleDesc(){
		return roleDesc;
	}
	public void setDomainName(java.lang.String  domainName){
		this.domainName = domainName;
	}
	public java.lang.String getDomainName(){
		return domainName;
	}
	public void setRoleRank(java.lang.Integer  roleRank){
		this.roleRank = roleRank;
	}
	public java.lang.Integer getRoleRank(){
		return roleRank;
	}
	public void setStartTime(java.sql.Timestamp  startTime){
		this.startTime = startTime;
	}
	public java.sql.Timestamp getStartTime(){
		return startTime;
	}
	public void setExpireTime(java.sql.Timestamp  expireTime){
		this.expireTime = expireTime;
	}
	public java.sql.Timestamp getExpireTime(){
		return expireTime;
	}
	public void setCreate(user.UserInfo  create){
		this.create = create;
	}
	public user.UserInfo getCreate(){
		return create;
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
	public void setRoleStatus(java.lang.Integer  roleStatus){
		this.roleStatus = roleStatus;
	}
	public java.lang.Integer getRoleStatus(){
		return roleStatus;
	}
	public void setRoleMaxRank(java.lang.Integer  roleMaxRank){
		this.roleMaxRank = roleMaxRank;
	}
	public java.lang.Integer getRoleMaxRank(){
		return roleMaxRank;
	}
	public void setRoleModuleAuths(java.util.Set<auth.RoleModuleAuth>  roleModuleAuths){
		this.roleModuleAuths = roleModuleAuths;
	}
	public java.util.Set<auth.RoleModuleAuth> getRoleModuleAuths(){
		return roleModuleAuths;
	}
	public void setUserRoleMappings(java.util.Set<auth.UserRoleMapping>  userRoleMappings){
		this.userRoleMappings = userRoleMappings;
	}
	public java.util.Set<auth.UserRoleMapping> getUserRoleMappings(){
		return userRoleMappings;
	}
}
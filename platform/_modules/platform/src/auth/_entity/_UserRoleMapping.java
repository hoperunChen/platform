package auth._entity;
import com.orm.entity.OrmEntity;
public class _UserRoleMapping extends OrmEntity {
	private static final long serialVersionUID = 1L;
	//FIELDS
	private java.lang.Integer sid;	//SID
	private java.sql.Timestamp startTime;	//����ʱ��
	private java.sql.Timestamp expireTime;	//��ֹʱ��
	private user.UserInfo creater;	//������
	private java.sql.Timestamp createTime;	//����ʱ��
	private user.UserInfo updater;	//�޸���
	private java.sql.Timestamp updateTime;	//�޸�ʱ��
	private auth.RoleInfo roleInfo;	//��ɫ
	private user.UserInfo userInfo;	//�û�
	//GETTER&SETTER
	public void setSid(java.lang.Integer  sid){
		this.sid = sid;
	}
	public java.lang.Integer getSid(){
		return sid;
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
}
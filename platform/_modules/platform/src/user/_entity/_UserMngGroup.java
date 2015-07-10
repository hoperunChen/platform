package user._entity;
import com.orm.entity.OrmEntity;
public class _UserMngGroup extends OrmEntity {
	private static final long serialVersionUID = 1L;
	//FIELDS
	private java.lang.Integer sid;	//SID
	private java.lang.String mngGroupName;	//��������
	private java.lang.String mngGroupDesc;	//����˵��
	private user.UserInfo creater;	//������
	private java.sql.Timestamp createTime;	//����ʱ��
	private user.UserInfo updater;	//�޸���
	private java.sql.Timestamp updateTime;	//�޸�ʱ��
	private java.util.Set<user.UserInfo> userInfos;	//null
	//GETTER&SETTER
	public void setSid(java.lang.Integer  sid){
		this.sid = sid;
	}
	public java.lang.Integer getSid(){
		return sid;
	}
	public void setMngGroupName(java.lang.String  mngGroupName){
		this.mngGroupName = mngGroupName;
	}
	public java.lang.String getMngGroupName(){
		return mngGroupName;
	}
	public void setMngGroupDesc(java.lang.String  mngGroupDesc){
		this.mngGroupDesc = mngGroupDesc;
	}
	public java.lang.String getMngGroupDesc(){
		return mngGroupDesc;
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
	public void setUserInfos(java.util.Set<user.UserInfo>  userInfos){
		this.userInfos = userInfos;
	}
	public java.util.Set<user.UserInfo> getUserInfos(){
		return userInfos;
	}
}
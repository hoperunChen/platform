package module._entity;
import com.orm.entity.OrmEntity;
public class _SubSystemList extends OrmEntity {
	private static final long serialVersionUID = 1L;
	//FIELDS
	private java.lang.Integer sid;	//SID
	private java.lang.String sysNo;	//子系统编号
	private java.lang.String sysName;	//子系统名称
	private java.lang.String sysDesc;	//子系统说明
	private java.sql.Timestamp startTime;	//启用时间
	private user.UserInfo creater;	//创建人
	private java.sql.Timestamp createTime;	//创建时间
	private user.UserInfo updater;	//修改人
	private java.sql.Timestamp updateTime;	//修改时间
	private java.lang.Integer subSysStatus;	//子系统状态
	private java.util.Set<module.ModuleList> moduleLists;	//null
	//GETTER&SETTER
	public void setSid(java.lang.Integer  sid){
		this.sid = sid;
	}
	public java.lang.Integer getSid(){
		return sid;
	}
	public void setSysNo(java.lang.String  sysNo){
		this.sysNo = sysNo;
	}
	public java.lang.String getSysNo(){
		return sysNo;
	}
	public void setSysName(java.lang.String  sysName){
		this.sysName = sysName;
	}
	public java.lang.String getSysName(){
		return sysName;
	}
	public void setSysDesc(java.lang.String  sysDesc){
		this.sysDesc = sysDesc;
	}
	public java.lang.String getSysDesc(){
		return sysDesc;
	}
	public void setStartTime(java.sql.Timestamp  startTime){
		this.startTime = startTime;
	}
	public java.sql.Timestamp getStartTime(){
		return startTime;
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
	public void setSubSysStatus(java.lang.Integer  subSysStatus){
		this.subSysStatus = subSysStatus;
	}
	public java.lang.Integer getSubSysStatus(){
		return subSysStatus;
	}
	public void setModuleLists(java.util.Set<module.ModuleList>  moduleLists){
		this.moduleLists = moduleLists;
	}
	public java.util.Set<module.ModuleList> getModuleLists(){
		return moduleLists;
	}
}
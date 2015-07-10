package module._entity;
import com.orm.entity.OrmEntity;
public class _ModuleList extends OrmEntity {
	private static final long serialVersionUID = 1L;
	//FIELDS
	private java.lang.Integer sid;	//SID
	private java.lang.String modNo;	//模块编号
	private java.lang.String modName;	//模块名称
	private java.lang.String modDesc;	//模块描述
	private java.sql.Timestamp startTime;	//启用时间
	private java.lang.String appEntry;	//模块入口
	private user.UserInfo creater;	//创建人
	private java.sql.Timestamp createTime;	//创建时间
	private user.UserInfo updater;	//修改人
	private java.sql.Timestamp updateTime;	//修改时间
	private module.SubSystemList subSystemList;	//子系统
	private java.lang.Integer moduleStatus;	//模块状态
	private java.util.Set<module.ModuleFuncList> moduleFuncLists;	//null
	//GETTER&SETTER
	public void setSid(java.lang.Integer  sid){
		this.sid = sid;
	}
	public java.lang.Integer getSid(){
		return sid;
	}
	public void setModNo(java.lang.String  modNo){
		this.modNo = modNo;
	}
	public java.lang.String getModNo(){
		return modNo;
	}
	public void setModName(java.lang.String  modName){
		this.modName = modName;
	}
	public java.lang.String getModName(){
		return modName;
	}
	public void setModDesc(java.lang.String  modDesc){
		this.modDesc = modDesc;
	}
	public java.lang.String getModDesc(){
		return modDesc;
	}
	public void setStartTime(java.sql.Timestamp  startTime){
		this.startTime = startTime;
	}
	public java.sql.Timestamp getStartTime(){
		return startTime;
	}
	public void setAppEntry(java.lang.String  appEntry){
		this.appEntry = appEntry;
	}
	public java.lang.String getAppEntry(){
		return appEntry;
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
	public void setSubSystemList(module.SubSystemList  subSystemList){
		this.subSystemList = subSystemList;
	}
	public module.SubSystemList getSubSystemList(){
		return subSystemList;
	}
	public void setModuleStatus(java.lang.Integer  moduleStatus){
		this.moduleStatus = moduleStatus;
	}
	public java.lang.Integer getModuleStatus(){
		return moduleStatus;
	}
	public void setModuleFuncLists(java.util.Set<module.ModuleFuncList>  moduleFuncLists){
		this.moduleFuncLists = moduleFuncLists;
	}
	public java.util.Set<module.ModuleFuncList> getModuleFuncLists(){
		return moduleFuncLists;
	}
}
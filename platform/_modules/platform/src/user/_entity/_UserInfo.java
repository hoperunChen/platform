package user._entity;
import com.orm.entity.OrmEntity;
public class _UserInfo extends OrmEntity {
	private static final long serialVersionUID = 1L;
	//FIELDS
	private java.lang.Integer sid;	//SID
	private java.lang.String userName;	//用户名
	private java.lang.String hmName;	//姓名
	private java.lang.String userPass;	//密码
	private java.lang.String idNum;	//身份证号
	private java.lang.String showName;	//昵称
	private java.lang.Double userOrder;	//排序
	private java.lang.Integer userGender;	//性别
	private java.lang.String residentialAddr;	//居住地址
	private java.lang.String homeTown;	//家乡
	private java.lang.Integer defaultAddr;	//默认地址
	private java.lang.String userTitle;	//职务
	private java.lang.String userEmail;	//电子信箱
	private java.lang.String userSite;	//个人主页
	private java.lang.String userNote;	//说明
	private java.lang.String ipAddr;	//IP地址
	private java.lang.String macAddr;	//MAC地址
	private user.UserInfo creater;	//创建人
	private java.sql.Timestamp createTime;	//创建时间
	private user.UserInfo updater;	//修改人
	private java.sql.Timestamp updateTime;	//修改时间
	private java.lang.Integer userAvailId;	//在线状态
	private user.UserMngGroup userMngGroup;	//用户分组
	private java.lang.Integer userStatus;	//可用状态
	private java.lang.String namePic;	//照片
	private java.util.Set<user.UserInfo> userCreaters;	//null
	private java.util.Set<user.UserInfo> userUpdaters;	//null
	private java.util.Set<user.UserMngGroup> mngCreaters;	//null
	private java.util.Set<user.UserMngGroup> mngUpdaters;	//null
	private java.util.Set<auth.UserRoleMapping> roleMapCreaters;	//null
	private java.util.Set<auth.UserRoleMapping> roleMapUpdaters;	//null
	private java.util.Set<auth.RoleInfo> roleupdaters;	//null
	private java.util.Set<auth.RoleInfo> rolecreaters;	//null
	private java.util.Set<auth.UserRoleMapping> userRoleMappings;	//null
	private java.util.Set<auth.RoleModuleAuth> roleModuleAuths;	//null
	private java.util.Set<module.SubSystemList> subSysCreaters;	//null
	private java.util.Set<module.SubSystemList> subSysUpdaters;	//null
	private java.util.Set<module.ModuleFuncList> moduleFuncCreaters;	//null
	private java.util.Set<module.ModuleFuncList> moduleFuncUpdaters;	//null
	private java.util.Set<module.ModuleList> moduleCreaters;	//null
	private java.util.Set<module.ModuleList> moduleUpdaters;	//null
	//GETTER&SETTER
	public void setSid(java.lang.Integer  sid){
		this.sid = sid;
	}
	public java.lang.Integer getSid(){
		return sid;
	}
	public void setUserName(java.lang.String  userName){
		this.userName = userName;
	}
	public java.lang.String getUserName(){
		return userName;
	}
	public void setHmName(java.lang.String  hmName){
		this.hmName = hmName;
	}
	public java.lang.String getHmName(){
		return hmName;
	}
	public void setUserPass(java.lang.String  userPass){
		this.userPass = userPass;
	}
	public java.lang.String getUserPass(){
		return userPass;
	}
	public void setIdNum(java.lang.String  idNum){
		this.idNum = idNum;
	}
	public java.lang.String getIdNum(){
		return idNum;
	}
	public void setShowName(java.lang.String  showName){
		this.showName = showName;
	}
	public java.lang.String getShowName(){
		return showName;
	}
	public void setUserOrder(java.lang.Double  userOrder){
		this.userOrder = userOrder;
	}
	public java.lang.Double getUserOrder(){
		return userOrder;
	}
	public void setUserGender(java.lang.Integer  userGender){
		this.userGender = userGender;
	}
	public java.lang.Integer getUserGender(){
		return userGender;
	}
	public void setResidentialAddr(java.lang.String  residentialAddr){
		this.residentialAddr = residentialAddr;
	}
	public java.lang.String getResidentialAddr(){
		return residentialAddr;
	}
	public void setHomeTown(java.lang.String  homeTown){
		this.homeTown = homeTown;
	}
	public java.lang.String getHomeTown(){
		return homeTown;
	}
	public void setDefaultAddr(java.lang.Integer  defaultAddr){
		this.defaultAddr = defaultAddr;
	}
	public java.lang.Integer getDefaultAddr(){
		return defaultAddr;
	}
	public void setUserTitle(java.lang.String  userTitle){
		this.userTitle = userTitle;
	}
	public java.lang.String getUserTitle(){
		return userTitle;
	}
	public void setUserEmail(java.lang.String  userEmail){
		this.userEmail = userEmail;
	}
	public java.lang.String getUserEmail(){
		return userEmail;
	}
	public void setUserSite(java.lang.String  userSite){
		this.userSite = userSite;
	}
	public java.lang.String getUserSite(){
		return userSite;
	}
	public void setUserNote(java.lang.String  userNote){
		this.userNote = userNote;
	}
	public java.lang.String getUserNote(){
		return userNote;
	}
	public void setIpAddr(java.lang.String  ipAddr){
		this.ipAddr = ipAddr;
	}
	public java.lang.String getIpAddr(){
		return ipAddr;
	}
	public void setMacAddr(java.lang.String  macAddr){
		this.macAddr = macAddr;
	}
	public java.lang.String getMacAddr(){
		return macAddr;
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
	public void setUserAvailId(java.lang.Integer  userAvailId){
		this.userAvailId = userAvailId;
	}
	public java.lang.Integer getUserAvailId(){
		return userAvailId;
	}
	public void setUserMngGroup(user.UserMngGroup  userMngGroup){
		this.userMngGroup = userMngGroup;
	}
	public user.UserMngGroup getUserMngGroup(){
		return userMngGroup;
	}
	public void setUserStatus(java.lang.Integer  userStatus){
		this.userStatus = userStatus;
	}
	public java.lang.Integer getUserStatus(){
		return userStatus;
	}
	public void setNamePic(java.lang.String  namePic){
		this.namePic = namePic;
	}
	public java.lang.String getNamePic(){
		return namePic;
	}
	public void setUserCreaters(java.util.Set<user.UserInfo>  userCreaters){
		this.userCreaters = userCreaters;
	}
	public java.util.Set<user.UserInfo> getUserCreaters(){
		return userCreaters;
	}
	public void setUserUpdaters(java.util.Set<user.UserInfo>  userUpdaters){
		this.userUpdaters = userUpdaters;
	}
	public java.util.Set<user.UserInfo> getUserUpdaters(){
		return userUpdaters;
	}
	public void setMngCreaters(java.util.Set<user.UserMngGroup>  mngCreaters){
		this.mngCreaters = mngCreaters;
	}
	public java.util.Set<user.UserMngGroup> getMngCreaters(){
		return mngCreaters;
	}
	public void setMngUpdaters(java.util.Set<user.UserMngGroup>  mngUpdaters){
		this.mngUpdaters = mngUpdaters;
	}
	public java.util.Set<user.UserMngGroup> getMngUpdaters(){
		return mngUpdaters;
	}
	public void setRoleMapCreaters(java.util.Set<auth.UserRoleMapping>  roleMapCreaters){
		this.roleMapCreaters = roleMapCreaters;
	}
	public java.util.Set<auth.UserRoleMapping> getRoleMapCreaters(){
		return roleMapCreaters;
	}
	public void setRoleMapUpdaters(java.util.Set<auth.UserRoleMapping>  roleMapUpdaters){
		this.roleMapUpdaters = roleMapUpdaters;
	}
	public java.util.Set<auth.UserRoleMapping> getRoleMapUpdaters(){
		return roleMapUpdaters;
	}
	public void setRoleupdaters(java.util.Set<auth.RoleInfo>  roleupdaters){
		this.roleupdaters = roleupdaters;
	}
	public java.util.Set<auth.RoleInfo> getRoleupdaters(){
		return roleupdaters;
	}
	public void setRolecreaters(java.util.Set<auth.RoleInfo>  rolecreaters){
		this.rolecreaters = rolecreaters;
	}
	public java.util.Set<auth.RoleInfo> getRolecreaters(){
		return rolecreaters;
	}
	public void setUserRoleMappings(java.util.Set<auth.UserRoleMapping>  userRoleMappings){
		this.userRoleMappings = userRoleMappings;
	}
	public java.util.Set<auth.UserRoleMapping> getUserRoleMappings(){
		return userRoleMappings;
	}
	public void setRoleModuleAuths(java.util.Set<auth.RoleModuleAuth>  roleModuleAuths){
		this.roleModuleAuths = roleModuleAuths;
	}
	public java.util.Set<auth.RoleModuleAuth> getRoleModuleAuths(){
		return roleModuleAuths;
	}
	public void setSubSysCreaters(java.util.Set<module.SubSystemList>  subSysCreaters){
		this.subSysCreaters = subSysCreaters;
	}
	public java.util.Set<module.SubSystemList> getSubSysCreaters(){
		return subSysCreaters;
	}
	public void setSubSysUpdaters(java.util.Set<module.SubSystemList>  subSysUpdaters){
		this.subSysUpdaters = subSysUpdaters;
	}
	public java.util.Set<module.SubSystemList> getSubSysUpdaters(){
		return subSysUpdaters;
	}
	public void setModuleFuncCreaters(java.util.Set<module.ModuleFuncList>  moduleFuncCreaters){
		this.moduleFuncCreaters = moduleFuncCreaters;
	}
	public java.util.Set<module.ModuleFuncList> getModuleFuncCreaters(){
		return moduleFuncCreaters;
	}
	public void setModuleFuncUpdaters(java.util.Set<module.ModuleFuncList>  moduleFuncUpdaters){
		this.moduleFuncUpdaters = moduleFuncUpdaters;
	}
	public java.util.Set<module.ModuleFuncList> getModuleFuncUpdaters(){
		return moduleFuncUpdaters;
	}
	public void setModuleCreaters(java.util.Set<module.ModuleList>  moduleCreaters){
		this.moduleCreaters = moduleCreaters;
	}
	public java.util.Set<module.ModuleList> getModuleCreaters(){
		return moduleCreaters;
	}
	public void setModuleUpdaters(java.util.Set<module.ModuleList>  moduleUpdaters){
		this.moduleUpdaters = moduleUpdaters;
	}
	public java.util.Set<module.ModuleList> getModuleUpdaters(){
		return moduleUpdaters;
	}
}
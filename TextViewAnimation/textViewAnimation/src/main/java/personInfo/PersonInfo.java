package personInfo;

public class PersonInfo {

	private String userName;//�û���
	private String isLogin;//��¼��״̬
	private String loginTime;//�ϴε�¼��ʱ��
	private String isLoginRemember;//�Ƿ��ס����
	private String isStorageBuffer;//�Ƿ�洢����
	private String firstLogin;//��һ�ε�½
	private String touxiangurl;//ͷ��·��
	private String id;//�û���id
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTouxiangurl() {
		return touxiangurl;
	}
	public void setTouxiangurl(String touxiangurl) {
		this.touxiangurl = touxiangurl;
	}
	public String getFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(String firstLogin) {
		this.firstLogin = firstLogin;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIsLogin() {
		return isLogin;
	}
	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getIsLoginRemember() {
		return isLoginRemember;
	}
	public void setIsLoginRemember(String isLoginRemember) {
		this.isLoginRemember = isLoginRemember;
	}
	public String getIsStorageBuffer() {
		return isStorageBuffer;
	}
	public void setIsStorageBuffer(String isStorageBuffer) {
		this.isStorageBuffer = isStorageBuffer;
	}
	@Override
	public String toString() {
		return "userName:" + userName + "\nisLogin:" + isLogin
				+ "\nloginTime:" + loginTime + "\nisLoginRemember:"
				+ isLoginRemember + "\nisStorageBuffer:" + isStorageBuffer
				+ "\nfirstLogin:" + firstLogin + "\ntouxiangurl:"+touxiangurl+"\nid:"+id+"\n";
	}
	
	

	
	
}

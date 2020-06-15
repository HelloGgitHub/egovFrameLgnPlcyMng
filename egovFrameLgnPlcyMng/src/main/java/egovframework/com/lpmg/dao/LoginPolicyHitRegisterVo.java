package egovframework.com.lpmg.dao;

/**
 * @title : 로그인정책 적중이력 관리 입출력 Vo 
 * @package : egovframework.com.lpmg.dao
 * @filename : LoginPolicyHitRegisterVo.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 로그인정책 적중이력 고나리 입출력 Vo
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 15.         "egov"           최초 생성(ver 1.0)
 * 
 */
public class LoginPolicyHitRegisterVo {

	private String usrId;
	private String plcyId;
	private String loginIp;
	
	
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	
	public String getPlcyId() {
		return plcyId;
	}
	public void setPlcyId(String plcyId) {
		this.plcyId = plcyId;
	}
	
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	
	
}

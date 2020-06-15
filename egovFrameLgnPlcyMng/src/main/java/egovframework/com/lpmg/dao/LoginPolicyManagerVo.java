package egovframework.com.lpmg.dao;

/**
 * @title : 로그인 정책관리 입출력 Vo
 * @package : egovframework.com.lpmg.dao
 * @filename : LoginPolicyManagerVo.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 로그인 정책관리 입출력 Vo
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 15.         "egov"           최초 생성(ver 1.0)
 * 
 */
public class LoginPolicyManagerVo {

	private String blkIp;
	private String addUsrid;
	private String addDt;

	private String policyId;
	private String policyNm;
	private String policyDc;
	private String policyBgndt;
	private String policyEnddt;
	private String policyAppyYn;
	private String policyRegistDt;
	private String policyAppyUsrid;

	private String usrId;
	private String policyHitDt;
	private String loginIp;
	
	
	
	public String getBlkIp() {
		return blkIp;
	}
	public void setBlkIp(String blkIp) {
		this.blkIp = blkIp;
	}
	public String getAddUsrid() {
		return addUsrid;
	}
	public void setAddUsrid(String addUsrid) {
		this.addUsrid = addUsrid;
	}
	public String getAddDt() {
		return addDt;
	}
	public void setAddDt(String addDt) {
		this.addDt = addDt;
	}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getPolicyNm() {
		return policyNm;
	}
	public void setPolicyNm(String policyNm) {
		this.policyNm = policyNm;
	}
	public String getPolicyDc() {
		return policyDc;
	}
	public void setPolicyDc(String policyDc) {
		this.policyDc = policyDc;
	}
	public String getPolicyBgndt() {
		return policyBgndt;
	}
	public void setPolicyBgndt(String policyBgndt) {
		this.policyBgndt = policyBgndt;
	}
	public String getPolicyEnddt() {
		return policyEnddt;
	}
	public void setPolicyEnddt(String policyEnddt) {
		this.policyEnddt = policyEnddt;
	}
	public String getPolicyAppyYn() {
		return policyAppyYn;
	}
	public void setPolicyAppyYn(String policyAppyYn) {
		this.policyAppyYn = policyAppyYn;
	}
	public String getPolicyRegistDt() {
		return policyRegistDt;
	}
	public void setPolicyRegistDt(String policyRegistDt) {
		this.policyRegistDt = policyRegistDt;
	}
	public String getPolicyAppyUsrid() {
		return policyAppyUsrid;
	}
	public void setPolicyAppyUsrid(String policyAppyUsrid) {
		this.policyAppyUsrid = policyAppyUsrid;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getPolicyHitDt() {
		return policyHitDt;
	}
	public void setPolicyHitDt(String policyHitDt) {
		this.policyHitDt = policyHitDt;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	
	
}

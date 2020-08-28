package egovframework.com.lpmg.dao;

/**
 * @title : 차단IP목록관리 입출력 Vo 
 * @package : egovframework.com.lpmg.dao
 * @filename : LoginPolicyDpIpVo.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 차단IP목록관리 입출력 Vo
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 15.         "egov"           최초 생성(ver 1.0)
 * 
 */
public class LoginPolicyDpIpVo {

	private String blkIp;
	private String blkIpCidr;
	private String blkIpEd;
	private String blkIpTyp;
	private String blkIpNm;
	private String blkIpDc;
	private String addUsrid;
	
	
	public String getBlkIp() {
		return blkIp;
	}
	public void setBlkIp(String blkIp) {
		this.blkIp = blkIp;
	}
	public String getBlkIpCidr() {
		return blkIpCidr;
	}
	public void setBlkIpCidr(String blkIpCidr) {
		this.blkIpCidr = blkIpCidr;
	}
	public String getBlkIpEd() {
		return blkIpEd;
	}
	public void setBlkIpEd(String blkIpEd) {
		this.blkIpEd = blkIpEd;
	}
	public String getBlkIpTyp() {
		return blkIpTyp;
	}
	public void setBlkIpTyp(String blkIpTyp) {
		this.blkIpTyp = blkIpTyp;
	}
	public String getBlkIpNm() {
		return blkIpNm;
	}
	public void setBlkIpNm(String blkIpNm) {
		this.blkIpNm = blkIpNm;
	}
	public String getBlkIpDc() {
		return blkIpDc;
	}
	public void setBlkIpDc(String blkIpDc) {
		this.blkIpDc = blkIpDc;
	}
	public String getAddUsrid() {
		return addUsrid;
	}
	public void setAddUsrid(String addUsrid) {
		this.addUsrid = addUsrid;
	}
	
}

package egovframework.com.lpmg.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginPolicyManagerInputParamVo {

	private int pageSize;
	private int page;
	
	private String UsrId;
	private String authCd;
	
	
	public String getUsrId() {
		return UsrId;
	}
	public void setUsrId(String usrId) {
		UsrId = usrId;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public String getAuthCd() {
		return authCd;
	}
	public void setAuthCd(String authCd) {
		this.authCd = authCd;
	}
	
}

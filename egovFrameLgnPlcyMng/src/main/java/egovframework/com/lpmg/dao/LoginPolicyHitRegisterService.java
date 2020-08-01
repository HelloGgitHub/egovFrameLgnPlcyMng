package egovframework.com.lpmg.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.util.SubnetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComUtil;

/**
 * @title : 로그인 정책 적중이력 관리 Service 
 * @package : egovframework.com.lpmg.dao
 * @filename : LoginPolicyHitRegisterService.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 로그인 정책 적중이력 관리 Service
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 15.         "egov"           최초 생성(ver 1.0)
 * 
 */
@Service
@Transactional
public class LoginPolicyHitRegisterService {
	
	@Value("${spring.whois.url}")
    String whoIsUrl;
	
	@Autowired
    private LoginPolicyHitRegisterDao mapper;
	

	/**
	 * @name : selectHitRgstList(로그인정책 적중목록 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : List<HashMap<Object,Object>>
	 */
	public List<HashMap<Object, Object>> selectHitRgstList(Map<Object, Object> param) {
        return mapper.selectHitRgstList(param);
    }

	/**
	 * @name : selectHitRgstDetailList(로그인정책 적중목록 상세조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : List<HashMap<Object,Object>>
	 */
	public List<HashMap<Object, Object>> selectHitRgstDetailList(Map<Object, Object> param) {
		
		List<HashMap<Object, Object>> rtnLst = new ArrayList<HashMap<Object, Object>> ();
		List<HashMap<Object, Object>> sqlLst = new ArrayList<HashMap<Object, Object>> ();
		
		sqlLst = mapper.selectHitRgstDetailList(param);
		
		for(int i = 0; sqlLst.size() > i; i++) {
			HashMap<Object, Object> rtnMap= new HashMap<Object, Object>();
			rtnMap = sqlLst.get(i);
			rtnMap.put("policy_hit_dt", ComUtil.dbDatetoDataTimeFrm(rtnMap.get("policy_hit_dt").toString()));
			rtnLst.add(rtnMap);
		}
		
        return rtnLst;
    }
	
	
	/**
	 * @name : selectHitRgstUser(사용자 기준 로그인정책 적중이력)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : List<HashMap<Object,Object>>
	 */
	public List<HashMap<Object, Object>> selectHitRgstUser(Map<Object, Object> param) {
        return mapper.selectHitRgstUser(param);
    }	

	/**
	 * @name : insertLgHitRgst(로그인정책 적중이력 저장)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int insertLgHitRgst(Map<Object, Object> param) {
        return mapper.insertLgPlcyHitRgst(param);
    }
	 
	/**
	 * @name : deleteLgPlcyHitUsrRgst(사용자기준 로그인 정책적중이력 삭제)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int deleteLgPlcyHitUsrRgst(Map<Object, Object> param) {
        return mapper.deleteLgPlcyHitUsrRgst(param);
    }
	
	/**
	 * @name : deleteLgPlcyHitPlcyRgst(정책기준 로그인 정책적중이력 삭제)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int deleteLgPlcyHitPlcyRgst(Map<Object, Object> param) {
        return mapper.deleteLgPlcyHitPlcyRgst(param);
    }

	/**
	 * @name : deleteLgPlcyHitRgst(로그인 정책적중이력 삭제)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int deleteHtlist(Map<Object, Object> param) {
        return mapper.deleteHtlist(param);
    }

	
	/**
	 * @name : selectLgPlcyHitlMsg(로그인 적중 이력 메시지 출력)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @throws Exception 
	 * @return_type : HashMap<String,Object>
	 */
	public HashMap<String, Object> selectLgPlcyHitlMsg(Map<Object, Object> param) throws Exception {
		
		HashMap<String, Object> rtnMap = new HashMap<String, Object>();
		HashMap<String, Object> rtnSqlMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> plcyList = new ArrayList<HashMap<String, Object>> ();
		List<HashMap<String, Object>> sqlList = new ArrayList<HashMap<String, Object>> ();
		boolean htYn = false;
		//정책목록 조회
		param.put("PDT", ComUtil.getTime("yyyyMMdd"));
		plcyList = mapper.selectLgPlcyList(param);
		if(plcyList.size() > 0) {
			for(int i =0; plcyList.size() > i; i++) {
				Map<String,Object> listMap = new HashMap<String, Object>();
				listMap = plcyList.get(i);
				
				//ID/PW입력 오류 로그인 제한
				if(htYn==false && "POLCY001".equals(listMap.get("policy_id"))) {
					//사용자 ID/PW조회
					param.put("PLCYID", "POLCY001"); //IDPW 오류횟수 체크
					param.put("LOGINIPCNTR", "-");
					rtnSqlMap.clear();
					rtnSqlMap = mapper.selectUserPwErCnt(param);
					if(Integer.parseInt(rtnSqlMap.get("cnt").toString()) > Integer.parseInt(listMap.get("policy_htmxcnt").toString())) {//오류 횟수 카운트
						htYn = true;
						rtnMap.put("MSG", "패스워드 오류 횟수가 총 " + listMap.get("policy_htmxcnt") + "회 이상으로 ID가 잠겼습니다.\n관리자에게 문의하세요.");
						rtnMap.put("CKCD", "1");
					}
				}
				
				//차단 대상 IP접근 로그인 제한
				if(htYn==false && "POLCY002".equals(listMap.get("policy_id"))) {
					
					
					SubnetUtils subnetUtils = new SubnetUtils("120.131.5.130/26");
			        subnetUtils.setInclusiveHostCount(true);    //network,broadcast ip 포함, false:불포함
					
			        subnetUtils.getInfo().isInRange("120.131.5.130");
			        
			        
					rtnSqlMap.clear();
					rtnSqlMap = mapper.selectUserIpCk(param);
					if(Integer.parseInt(rtnSqlMap.get("cnt").toString()) > 0) {  //IDPW값이 저장된 값이랑 다를때
						htYn = true;
						param.put("PLCYID", "POLCY002"); //IDPW 오류횟수 저장
						param.put("POLICYHITDT", ComUtil.getTime("yyyyMMddHHmmss"));
						param.put("LOGINIPCNTR", "-");
						mapper.insertLgPlcyHitRgst(param);
						rtnMap.put("MSG", "접근차단대상 위치에서 접속하고 계십니다. \n로그인에 실패 하였습니다.\n관리자에게 문의하세요.");
						rtnMap.put("CKCD", "1");
					}
				}
				
				//해외IP 차단
				if(htYn==false && "POLCY003".equals(listMap.get("policy_id"))) {
					param.put("PLCYID", "POLCY003"); //IDPW 오류횟수 저장
					param.put("POLICYHITDT", ComUtil.getTime("yyyyMMddHHmmss"));
					
					String data = mapper.selectWhoisKey(param).get("key");
					ObjectMapper om = new ObjectMapper();
					
					String query = "query="+Inet4Address.getLocalHost().getHostAddress();
					String key = "key="+data;
					String answer = "answer=json"; 
					String url = whoIsUrl+"?"+query+"&"+key+"&"+answer;
					String whoisIpInfo = getUrlConn("GET", url);
					
					if(!"".equals(whoisIpInfo) &&  null != whoisIpInfo) {
						HashMap<String, Object> map = om.readValue(whoisIpInfo, HashMap.class);
						rtnSqlMap.clear();
						rtnSqlMap = (HashMap<String, Object>)map.get("whois");
						rtnSqlMap.put("countryCode","US");///////////////////////////////////////////////////////////////////////////////테스트
						if(!"KR".equals(rtnSqlMap.get("countryCode")) && !"none".equals(rtnSqlMap.get("countryCode"))) { 
							htYn = true;
							param.put("LOGINIPCNTR", rtnSqlMap.get("countryCode"));
							mapper.insertLgPlcyHitRgst(param);
							rtnMap.put("MSG", "해당 서비스는 해외에서 접근이 불가능합니다. \n로그인에 실패 하였습니다.");
							rtnMap.put("CKCD", "1");
						}
					}
				}
			}
		}
		
		
		
		
		//차단 대상 정책이 적중된 내용이 없을경우 일반 로그인
		//사용자 ID/PW조회
		if(htYn== false) { //정책로직에 걸린게 없을 경우 정상 로그인 처리
			rtnSqlMap.clear();
			rtnSqlMap = mapper.selectUserPwCk(param);
			if(rtnSqlMap == null) {  //사용자 정보가 없을때 
				rtnMap.put("MSG", "등록된 사용자가 없습니다.");
				rtnMap.put("CKCD", "1");
			}else if(!param.get("USRPW").equals(rtnSqlMap.get("password"))) {  //IDPW값이 저장된 값이랑 다를때
				param.put("PLCYID", "POLCY001"); //IDPW 오류횟수 저장
				param.put("POLICYHITDT", ComUtil.getTime("yyyyMMddHHmmss"));
				mapper.insertLgPlcyHitRgst(param);
				rtnMap.put("MSG", "패스워드가 올바르지 않습니다.");
				rtnMap.put("CKCD", "1");
			}else if(param.get("USRPW").equals(rtnSqlMap.get("password"))) {  //IDPW값이 저장된 값이랑 다를때
				List<HashMap<String, Object>> usrDt = new ArrayList<HashMap<String, Object>>();
				param.put("PLCYID", "POLCY001"); //IDPW 오류횟수 저장
				mapper.deleteHtlist(param); //정상 로그인 시 이전 패스워드 실패 이력 삭제
				usrDt = mapper.selectUserDetail(param);
				rtnMap.put("list", usrDt);
				rtnMap.put("MSG", param.get("USRID") + "님 환영합니다.");
				rtnMap.put("CKCD", "0");
			}else {
				rtnMap.put("MSG", "ID/PW가 올바르지 않습니다.");
				rtnMap.put("CKCD", "1");
			}
		}

		return rtnMap;
    }

	public String getUrlConn(String Type, String strUrl) {
		String rtn = "";
		
		try {
			URL url = new URL(strUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setRequestMethod(Type);
			con.setDoOutput(false); 
			
			StringBuilder sb = new StringBuilder();
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(
				new InputStreamReader(con.getInputStream(), "utf-8"));
				
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line); //.append("\n");
				}
				br.close();
				rtn = sb.toString();
				
			} else {
				System.out.println("http_ok no >>>\n" + con.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rtn;
	}

	
}

package egovframework.com.lpmg.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Autowired
    private LoginPolicyHitRegisterDao mapper;
	

	/**
	 * @name : selectHitRgstList(로그인정책 적중목록 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : List<HashMap<Object,Object>>
	 */
	public List<HashMap<Object, Object>> selectHitRgstList() {
        return mapper.selectHitRgstList();
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
	 * @name : selectLgPlcyHitlMsg(로그인 적중 이력 메시지 출력)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : HashMap<String,Object>
	 */
	public HashMap<String, Object> selectLgPlcyHitlMsg(Map<Object, Object> param) {
		HashMap<String, Object> rtnMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> sqlList = new ArrayList<HashMap<String, Object>> ();
		sqlList = mapper.selectLgPlcyHitlMsg(param);
		String msg = "- @" + param.get("USRID") + "님 적중이력 -";
		for(int i =0; sqlList.size() > i; i++) {
			Map<String, Object> rtMap = new HashMap<String, Object>();
			rtMap = sqlList.get(i);
			msg += "\n" + rtMap.get("POLICY_NM") + " : " + rtMap.get("CNT").toString();
		}
		msg += "\n 입니다.";
		rtnMap.put("MSG", msg);
		
        return rtnMap;
    }

}

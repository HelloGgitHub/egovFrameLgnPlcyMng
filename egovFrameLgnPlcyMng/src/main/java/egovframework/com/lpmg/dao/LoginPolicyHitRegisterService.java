package egovframework.com.lpmg.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginPolicyHitRegisterService {
	
	@Autowired
    private LoginPolicyHitRegisterDao mapper;
	
	
	//차단대상IP 목록 조회
	public List<HashMap<Object, Object>> selectHitRgstList() {
        return mapper.selectHitRgstList();
    }	

	//차단대상IP 목록 조회
	public List<HashMap<Object, Object>> selectHitRgstUser(Map<Object, Object> param) {
        return mapper.selectHitRgstUser(param);
    }	

	//차단대상IP 등록
	public int insertLgHitRgst(Map<Object, Object> param) {
        return mapper.insertLgPlcyHitRgst(param);
    }
	 
	//차단대상IP 삭제
	public int deleteLgPlcyHitUsrRgst(Map<Object, Object> param) {
        return mapper.deleteLgPlcyHitUsrRgst(param);
    }
	
	//차단대상IP 삭제
	public int deleteLgPlcyHitPlcyRgst(Map<Object, Object> param) {
        return mapper.deleteLgPlcyHitPlcyRgst(param);
    }

	
	//로그인 적중 이력 메시지 출력
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

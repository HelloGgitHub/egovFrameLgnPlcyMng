package egovframework.com.lpmg.dao;

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

	//차단대상IP 기등록여부 확인
	public int selectLgPlcyHitRgstCnt(Map<Object, Object> param) {
        return mapper.selectLgPlcyHitRgstCnt(param);
    }
	//차단대상IP 등록
	public int insertLgHitRgst(Map<Object, Object> param) {
        return mapper.insertLgPlcyHitRgst(param);
    }
	 
	//차단대상IP 삭제
	public int deleteLgPlcyHitRgst(Map<Object, Object> param) {
        return mapper.deleteLgPlcyHitRgst(param);
    }

}

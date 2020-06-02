package egovframework.com.lpmg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginPolicyDpIpService {
	
	@Autowired
    private LoginPolicyDpIpDao mapper;
	
	
	//차단대상IP 목록 조회
	public List<HashMap<Object, Object>> selectDpIpList() {
        return mapper.selectDpIpList();
    }	

	//차단대상IP 기등록여부 확인
	public int selectLgPlcyDpIpCnt(Map<Object, Object> param) {
        return mapper.selectLgPlcyDpIpCnt(param);
    }
	//차단대상IP 등록
	public int insertLgDpIp(Map<Object, Object> param) {
        return mapper.insertLgPlcyDpIp(param);
    }
	 
	//차단대상IP 삭제
	public int deleteLgPlcyDpIp(Map<Object, Object> param) {
        return mapper.deleteLgPlcyDpIp(param);
    }

}

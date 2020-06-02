package egovframework.com.lpmg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginPolicyManagerService {
	
	@Autowired
    private LoginPolicyManagerDao mapper;
	
	
	//로그인정책 목록 조회
	public List<HashMap<Object, Object>> selectLgPlcyList() {
        return mapper.selectLgPlcyList();
    }	

	//권한상세정보 조회
	public HashMap<String, Object> selectLgPlcyDetail(Map<Object, Object> param) {
        return mapper.selectLgPlcyDetail(param);
    }
	
	//로그인정책 기등록여부 확인
	public int selectLgPlcyInfoCnt(Map<Object, Object> param) {
        return mapper.selectLgPlcyInfoCnt(param);
    }
	//로그인 정책 등록
	public int insertLgPlcyInfo(Map<Object, Object> param) {
        return mapper.insertLgPlcyInfo(param);
    }
	 
	//로그인 정책정보 변경
	public int updateLgPlcyInfo(Map<Object, Object> param) {
        return mapper.updateLgPlcyInfo(param);
    }	
	
	//로그인 정책정보 삭제
	public int deleteLgPlcyInfo(Map<Object, Object> param) {
        return mapper.deleteLgPlcyInfo(param);
    }


	//로그인 정책 적용여부 변경
	public int updateLgPlcyStat(Map<Object, Object> param) {
        return mapper.updateLgPlcyStat(param);
    }	

}

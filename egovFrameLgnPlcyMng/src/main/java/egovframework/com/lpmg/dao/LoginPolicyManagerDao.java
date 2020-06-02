package egovframework.com.lpmg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginPolicyManagerDao {
	

	//로그인정책 목록 조회
	List<HashMap<Object, Object>> selectLgPlcyList();

	//로그인정책 조회
	HashMap<String, Object> selectLgPlcyDetail(Map<Object, Object> param);

	
	//로그인정책 기등록여부 확인
	int selectLgPlcyInfoCnt(Map<Object, Object> param);

	//로그인 정책 등록
	int insertLgPlcyInfo(Map<Object, Object> param);
	
	//로그인 정책정보 변경
	int updateLgPlcyInfo(Map<Object, Object> param);	

	//로그인 정책 삭제
	int deleteLgPlcyInfo(Map<Object, Object> param);
	
	//로그인 정잭 상태코드 변경
	int updateLgPlcyStat(Map<Object, Object> param);	
	
}

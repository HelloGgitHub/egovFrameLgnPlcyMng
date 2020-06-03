package egovframework.com.lpmg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginPolicyHitRegisterDao {

	//차단대상IP 목록 조회
	List<HashMap<Object, Object>> selectHitRgstList();

	//차단대상IP 목록 조회
	List<HashMap<Object, Object>> selectHitRgstUsrList();

	//차단대상IP 기등록여부 확인
	int selectLgPlcyHitRgstCnt(Map<Object, Object> param);

	//차단대상IP 등록
	int insertLgPlcyHitRgst(Map<Object, Object> param);
	
	//차단대상IP 삭제
	int deleteLgPlcyHitRgst(Map<Object, Object> param);
	
}

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
	List<HashMap<Object, Object>> selectHitRgstUser(Map<Object, Object> param);

	//차단대상IP 등록
	int insertLgPlcyHitRgst(Map<Object, Object> param);
	
	//사용자 단위 적중이력 삭제
	int deleteLgPlcyHitUsrRgst(Map<Object, Object> param);
	//로그인정책 단위 적중이력 삭제
	int deleteLgPlcyHitPlcyRgst(Map<Object, Object> param);
	
	
	//로그인정책 조회
	List<HashMap<String, Object>> selectLgPlcyHitlMsg(Map<Object, Object> param);
}

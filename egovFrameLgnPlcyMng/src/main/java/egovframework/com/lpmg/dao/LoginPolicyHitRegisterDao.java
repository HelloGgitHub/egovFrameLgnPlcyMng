package egovframework.com.lpmg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @title : 로그인정책 적중이력 관리 Dao 
 * @package : egovframework.com.lpmg.dao
 * @filename : LoginPolicyHitRegisterDao.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 로그인정책 적중이력 관리 CRUD 모음.
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 15.         "egov"           최초 생성(ver 1.0)
 * 
 */
@Repository
@Mapper
public interface LoginPolicyHitRegisterDao {

	List<HashMap<Object, Object>> selectHitRgstList(Map<Object, Object> param);
	List<HashMap<Object, Object>> selectHitRgstDetailList(Map<Object, Object> param);
	List<HashMap<Object, Object>> selectHitRgstUser(Map<Object, Object> param);

	int insertLgPlcyHitRgst(Map<Object, Object> param);
	int deleteLgPlcyHitUsrRgst(Map<Object, Object> param);
	int deleteLgPlcyHitPlcyRgst(Map<Object, Object> param);
	
	List<HashMap<String, Object>> selectLgPlcyHitlMsg(Map<Object, Object> param);
	
	
	//로그인 처리(정책적용)
	List<HashMap<String, Object>> selectLgPlcyList(Map<Object, Object> param);
	//ID/PW체크
	HashMap<String, Object> selectUserPwCk(Map<Object, Object> param);
	//IP체크
	HashMap<String, Object> selectUserIpCk(Map<Object, Object> param);
	//정책오류 횟수확인
	HashMap<String, Object> selectUserPwErCnt(Map<Object, Object> param);
	//사용자 정보 조회
	List<HashMap<String, Object>> selectUserDetail(Map<Object, Object> param);
	//적중이력 삭제
	int deleteHtlist(Map<Object, Object> param);
	
	//whoisKey 조회
	HashMap<String, String> selectWhoisKey(Map<Object, Object> param);
	
	
		
}

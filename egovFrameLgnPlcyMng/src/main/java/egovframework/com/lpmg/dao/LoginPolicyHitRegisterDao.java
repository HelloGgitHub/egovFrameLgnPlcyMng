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

	List<HashMap<Object, Object>> selectHitRgstList();
	List<HashMap<Object, Object>> selectHitRgstUser(Map<Object, Object> param);

	int insertLgPlcyHitRgst(Map<Object, Object> param);
	int deleteLgPlcyHitUsrRgst(Map<Object, Object> param);
	int deleteLgPlcyHitPlcyRgst(Map<Object, Object> param);
	
	List<HashMap<String, Object>> selectLgPlcyHitlMsg(Map<Object, Object> param);
}

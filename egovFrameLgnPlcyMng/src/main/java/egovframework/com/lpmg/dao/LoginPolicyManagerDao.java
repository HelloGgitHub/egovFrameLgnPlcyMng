package egovframework.com.lpmg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @title : 로그인정책 관리 Dao 
 * @package : egovframework.com.lpmg.dao
 * @filename : LoginPolicyManagerDao.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 로그인정책관리 CRUD 모음
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
public interface LoginPolicyManagerDao {
	
	List<HashMap<Object, Object>> selectLgPlcyList();
	List<HashMap<Object, Object>> selectLgPlcyDetail(Map<Object, Object> param);
	
	int selectLgPlcyInfoCnt(Map<Object, Object> param);
	int insertLgPlcyInfo(Map<Object, Object> param);
	int updateLgPlcyInfo(Map<Object, Object> param);	

	int deleteLgPlcyInfo(Map<Object, Object> param);
	int updateLgPlcyStat(Map<Object, Object> param);	
	
}

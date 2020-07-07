package egovframework.com.lpmg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @title : 로그인정책 차단IP관리 Dao 
 * @package : egovframework.com.lpmg.dao
 * @filename : LoginPolicyDpIpDao.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 로그인정책 차단IP목록을 관리 하는 CRUD 모음.
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
public interface LoginPolicyDpIpDao {

	List<HashMap<Object, Object>> selectDpIpList();
	List<HashMap<Object, Object>> selectDpIpDetail(Map<Object, Object> param);
	int selectLgPlcyDpIpCnt(Map<Object, Object> param);
	int insertLgPlcyDpIp(Map<Object, Object> param);
	int deleteLgPlcyDpIp(Map<Object, Object> param);
	
}

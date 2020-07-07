package egovframework.com.lpmg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @title : 로그인정책 차단IP관리 Service  
 * @package : egovframework.com.lpmg.dao
 * @filename : LoginPolicyDpIpService.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 로그인정책 차단IP목록 관리 Service 모음
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 15.         "egov"           최초 생성(ver 1.0)
 * 
 */
@Service
@Transactional
public class LoginPolicyDpIpService {
	
	@Autowired
    private LoginPolicyDpIpDao mapper;
	
	/**
	 * @name : selectDpIpList(차단대상IP 목록 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : List<HashMap<Object,Object>>
	 */
	public List<HashMap<Object, Object>> selectDpIpList() {
        return mapper.selectDpIpList();
    }	

	/**
	 * @name : selectDpIpDetail(차단IP 정보 조회)
	 * @date : 2020. 7. 7.
	 * @author : "egov"
	 * @return_type : HashMap<String,Object>
	 */
	public List<HashMap<Object, Object>> selectDpIpDetail(Map<Object, Object> param) {
        return mapper.selectDpIpDetail(param);
    }
	
	/**
	 * @name : selectLgPlcyDpIpCnt(차단대상IP 기등록여부 확인)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int selectLgPlcyDpIpCnt(Map<Object, Object> param) {
        return mapper.selectLgPlcyDpIpCnt(param);
    }
	
	/**
	 * @name : insertLgDpIp(차단대상IP 등록)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int insertLgDpIp(Map<Object, Object> param) {
        return mapper.insertLgPlcyDpIp(param);
    }
	 
	/**
	 * @name : deleteLgPlcyDpIp(차단대상IP 삭제)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int deleteLgPlcyDpIp(Map<Object, Object> param) {
        return mapper.deleteLgPlcyDpIp(param);
    }

}

package egovframework.com.lpmg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @title : 로그인 정책관리 Service
 * @package : egovframework.com.lpmg.dao
 * @filename : LoginPolicyManagerService.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 로그인 정책관리 Service 모음
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
public class LoginPolicyManagerService {
	
	@Autowired
    private LoginPolicyManagerDao mapper;
	
	/**
	 * @name : selectLgPlcyList(로그인정책 목록 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : List<HashMap<Object,Object>>
	 */
	public List<HashMap<Object, Object>> selectLgPlcyList() {
        return mapper.selectLgPlcyList();
    }

	/**
	 * @name : selectLgPlcyDetail(로그인정책 상세정보 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : HashMap<String,Object>
	 */
	public List<HashMap<Object, Object>> selectLgPlcyDetail(Map<Object, Object> param) {
        return mapper.selectLgPlcyDetail(param);
    }
	
	/**
	 * @name : selectLgPlcyInfoCnt(로그인정책 기등록여부 확인)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int selectLgPlcyInfoCnt(Map<Object, Object> param) {
        return mapper.selectLgPlcyInfoCnt(param);
    }
	/**
	 * @name : insertLgPlcyInfo(로그인 정책 등록)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int insertLgPlcyInfo(Map<Object, Object> param) {
        return mapper.insertLgPlcyInfo(param);
    }
	 
	/**
	 * @name : updateLgPlcyInfo(로그인 정책정보 변경)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int updateLgPlcyInfo(Map<Object, Object> param) {
        return mapper.updateLgPlcyInfo(param);
    }	
	
	/**
	 * @name : deleteLgPlcyInfo(로그인 정책정보 삭제)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int deleteLgPlcyInfo(Map<Object, Object> param) {
        return mapper.deleteLgPlcyInfo(param);
    }


	/**
	 * @name : updateLgPlcyStat(로그인 정책 적용여부 변경)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int updateLgPlcyStat(Map<Object, Object> param) {
        return mapper.updateLgPlcyStat(param);
    }	

}

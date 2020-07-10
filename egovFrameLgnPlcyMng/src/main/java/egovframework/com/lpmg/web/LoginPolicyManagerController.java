package egovframework.com.lpmg.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComUtil;
import egovframework.com.lpmg.dao.LoginPolicyManagerService;
import egovframework.com.lpmg.dao.LoginPolicyManagerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @title : 로그인 정책관리
 * @package : egovframework.com.lpmg.web
 * @filename : LoginPolicyManagerController.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 로그인 정책관리 API모음
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 15.         "egov"           최초 생성(ver 1.0)
 * 
 */
@RestController
@Api(value = "LoginPolicyManagerController", description = "로그인정책 정보 관리 REST API")
@RequestMapping("/lgplcy")
public class LoginPolicyManagerController {
	
	@Autowired
	LoginPolicyManagerService lgnPlcyMngService;
	
	/**
	 * @name : LgPlcyList(로그인정책 목록 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @throws Exception 
	 * @return_type : String
	 * @desc : 로그인 정책 목록을 조회한다.
	 */
	@ApiOperation(value = "로그인정책 목록 조회")
	@GetMapping(path = "/list")
	public String LgPlcyList() throws Exception {
		
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		List<HashMap<Object, Object>> lst = new ArrayList<HashMap<Object, Object>>();
		
		try {
			lst = lgnPlcyMngService.selectLgPlcyList();
			rtnMap.put("list", lst);
			
			rtnMap.put("RESULTCD", "0");
			rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
		}catch (Exception e) {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "조회에 실패하였습니다.");
			e.printStackTrace();
		}
		
		rtn = om.writeValueAsString(rtnMap);
		
		return rtn;
	}
	
	/**
	 * @name : LgPlcyDetailInfo(로그인정책 정보 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 로그인 정책 정보를 조회한다.
	 */
	@ApiOperation(value = "로그인정책 정보 조회")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "plcyCd", value = "로그인정책 코드", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
	@GetMapping(path = "/detailInfo/{plcyCd}")
	public String LgPlcyDetailInfo(@PathVariable("plcyCd") String plcyCd) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		List<HashMap<Object, Object>> lst = new ArrayList<HashMap<Object, Object>>();
		
		try {
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
			sqlInpt.put("PLCYID", URLDecoder.decode(plcyCd		,"UTF-8"));
			
			lst = lgnPlcyMngService.selectLgPlcyDetail(sqlInpt);
			if(lst==null || lst.size()==0) {
				rtnMap = new HashMap<String, Object>();
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "대상건이 없습니다.");
			}else {
				rtnMap.put("list", lst);
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
			}
		}catch (Exception e) {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "조회에 실패하였습니다.");
			e.printStackTrace();
		}
		
		rtn = om.writeValueAsString(rtnMap);
		
		return rtn;
	}
	
	
	/**
	 * @name : InsertLgPlcyInfo(로그인 정책 등록)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 신규 로그인 정책을 등록한다.
	 */
	@ApiOperation(value = "로그인정책 등록", notes = "로그인 정책을 등록 합니다.")
	@PostMapping(path = "/addnew")
	public String InsertLgPlcyInfo(@RequestBody LoginPolicyManagerVo param) throws Exception {

		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		try {
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
	
			//입력값 파라미터 정의
			sqlInpt.put("PLCYID" 				,param.getPolicyId());
			sqlInpt.put("PLCYNM" 				,param.getPolicyNm());
			sqlInpt.put("PLCYDC" 				,param.getPolicyDc());
			sqlInpt.put("PLCYBGNDT" 			,param.getPolicyBgndt());
			sqlInpt.put("PLCYENDDT" 			,param.getPolicyEnddt());
			sqlInpt.put("PLCYAPPYYN" 			,param.getPolicyAppyYn());
			sqlInpt.put("POLICYHTMXCNT" 	,param.getPolicyHtmxCnt());
			sqlInpt.put("PLCYAPPYUSRID" 		,param.getPolicyAppyUsrid());
			sqlInpt.put("DT" 						,ComUtil.getTime("yyyyMMddHHmmss"));
			
			int rowCnt = lgnPlcyMngService.selectLgPlcyInfoCnt(sqlInpt);
			if(rowCnt == 0) {
				int inputCnt = lgnPlcyMngService.insertLgPlcyInfo(sqlInpt);
				if(inputCnt > 0) {
					rtnMap.put("RESULTCD", "0");
					rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
				}else {
					rtnMap.put("RESULTCD", "1");
					rtnMap.put("RESULTMSG", "등록에 실패 하였습니다.");
				}
			}else {
				rtnMap.put("RESULTCD", "1");
				rtnMap.put("RESULTMSG", "중복되는 정책ID가 있습니다.");
			}
		}catch (Exception e) {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "처리중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
		rtn = om.writeValueAsString(rtnMap);
		return rtn;
	}

	
	/**
	 * @name : LgPlcyChangeInfo(로그인정책 정보 변경)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 등록된 로그인 정책 정보의 내용을 수정한다.
	 */
	@ApiOperation(value = "로그인 정책 정보수정", notes = "로그인 정책정보를 수정한다.")
	@PutMapping(path = "/modifyInfo")
	public String LgPlcyChangeInfo(@RequestBody LoginPolicyManagerVo param) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		try {
			//입력값 파라미터 정의
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
			sqlInpt.put("PLCYID" 				,param.getPolicyId());
			sqlInpt.put("PLCYNM" 				,param.getPolicyNm());
			sqlInpt.put("PLCYDC" 				,param.getPolicyDc());
			sqlInpt.put("PLCYBGNDT" 			,param.getPolicyBgndt());
			sqlInpt.put("PLCYENDDT" 			,param.getPolicyEnddt());
			sqlInpt.put("PLCYAPPYYN" 			,param.getPolicyAppyYn());
			sqlInpt.put("POLICYHTMXCNT" 	,param.getPolicyHtmxCnt());
			sqlInpt.put("PLCYAPPYUSRID" 		,param.getPolicyAppyUsrid());
			sqlInpt.put("CHANGE_DT" 			,ComUtil.getTime("yyyyMMddHHmmss"));
			
			int inputCnt = lgnPlcyMngService.updateLgPlcyInfo(sqlInpt);
			if(inputCnt > 0) {
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
			}else {
				rtnMap.put("RESULTCD", "1");
				rtnMap.put("RESULTMSG", "등록된 로그인정책이 없습니다.");
			}
		}catch (Exception e) {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "처리중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
		rtn = om.writeValueAsString(rtnMap);
		return rtn;
	}
	
	
	/**
	 * @name : LgPlcyDeleteInfo(로그인정책 삭제)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 등록된 로그인 정책을 삭제 한다.
	 */
	@ApiOperation(value = "로그인정책 삭제", notes = "로그인정책을 삭제한다.")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "plcyId", value = "로그인정책 ID", required = true, dataType = "string", paramType = "query", defaultValue = "")
    })
	@DeleteMapping(path = "/delete")
	public String LgPlcyDeleteInfo(@RequestParam(value = "plcyId") String plcyId) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		try {
			//입력값 파라미터 정의
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
			sqlInpt.put("PLCYID", URLDecoder.decode(plcyId		,"UTF-8"));
			
			int inputCnt = lgnPlcyMngService.deleteLgPlcyInfo(sqlInpt);
			if(inputCnt > 0) {
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
			}else {
				rtnMap.put("RESULTCD", "1");
				rtnMap.put("RESULTMSG", "삭제 할 로그인 정책이 없습니다.");
			}
		}catch (Exception e) {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "처리중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
		
		rtn = om.writeValueAsString(rtnMap);
		return rtn;
	}

	
	/**
	 * @name : LgPlcyChangeStat(로그인정책 적용여부 등록)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 로그인 정책 적용 여부 변경을 관리 한다.
	 */
	@ApiOperation(value = "로그인정책 적용여부 등록", notes = "로그인 정책의 적용여부를 수정한다.")
	@PutMapping(path = "/modifyStat")
	public String LgPlcyChangeStat(@RequestBody LoginPolicyManagerVo param) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		try {
			//입력값 파라미터 정의
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
			sqlInpt.put("PLCYID" 			,param.getPolicyId());
			sqlInpt.put("PLCYAPPYYN" 		,param.getPolicyAppyYn());
			sqlInpt.put("PLCYAPPYUSRID" 	,param.getPolicyAppyUsrid());
			sqlInpt.put("CHANGE_DT" 		,ComUtil.getTime("yyyyMMddHHmmss"));
			
			int inputCnt = lgnPlcyMngService.updateLgPlcyInfo(sqlInpt);
			if(inputCnt > 0) {
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
			}else {
				rtnMap.put("RESULTCD", "1");
				rtnMap.put("RESULTMSG", "등록된 로그인정책이 없습니다.");
			}
		}catch (Exception e) {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "처리중 오류가 발생하였습니다.");
			e.printStackTrace();
		}
		rtn = om.writeValueAsString(rtnMap);
		return rtn;
	}
	
}

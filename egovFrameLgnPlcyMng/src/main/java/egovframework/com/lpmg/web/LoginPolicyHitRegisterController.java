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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.lpmg.dao.LoginPolicyHitRegisterService;
import egovframework.com.lpmg.dao.LoginPolicyHitRegisterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @title : 로그인정책 적중이력 정보관리
 * @package : egovframework.com.lpmg.web
 * @filename : LoginPolicyHitRegisterController.java
 * @author : egov
 * @since : 2020. 6. 4.
 * @version : 1.0
 * @desc : 로그인 정책 적중이력 정보를 처리하는 Restful API모음.
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 4.         egov              최초 생성(ver 1.0)
 * 
 */
@RestController
@Api(value = "LoginPolicyHitRegisterController", description = "로그인정책 적중이력 정보 관리 REST API")
@RequestMapping("/lgHRgt")
public class LoginPolicyHitRegisterController {
	
	@Autowired
	LoginPolicyHitRegisterService lgnPlcyHitRegstService;
	
	/**
	 * @name : LgPlcyHitRgtList(사용자별 로그인정책 적중이력 조회)
	 * @date : 2020. 6. 4.
	 * @author : egov
	 * @return_type : String
	 * @desc : 로그인정책 적중이력을 사용자, 정책 단위로 건수를 조회한 값을 리턴한다.
	 */
	@ApiOperation(value = "사용자별 로그인정책 적중이력 조회")
	@GetMapping(path = "/list")
	public String LgPlcyHitRgtList() {
		
		String rtn = "";
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		ObjectMapper om = new ObjectMapper();

		try {
			List<HashMap<Object, Object>> lst = new ArrayList<HashMap<Object, Object>>();
			lst = lgnPlcyHitRegstService.selectHitRgstList();
			rtnMap.put("list", lst);
			
			rtnMap.put("RESULTCD", "0");
			rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
		}catch (Exception e) {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "조회에 실패하였습니다.");
			e.getStackTrace();
		}
		
		try {
			rtn = om.writeValueAsString(rtnMap);
		} catch (JsonProcessingException e) {
			rtn = "json Mapper Error.";
			e.printStackTrace();
		}
		
		return rtn;
	}
	
	
	/**
	 * @name : LgPlcyHitRgtUser(사용자 로그인 정책 적중이력)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 로그인 정책에 적중한 이력을 조회한다.
	 */
	@ApiOperation(value = "사용자 로그인 정책 적중이력")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "userId", value = "사용자ID", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
	@GetMapping(path = "/list/{userId}")
	public String LgPlcyHitRgtUser(@PathVariable("userId") String userId) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		List<HashMap<Object, Object>> lst = new ArrayList<HashMap<Object, Object>>();
		
		try {
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
			sqlInpt.put("USRID", URLDecoder.decode(userId		,"UTF-8"));
			
			lst = lgnPlcyHitRegstService.selectHitRgstUser(sqlInpt);
			rtnMap.put("list", lst);
			rtnMap.put("RESULTCD", "0");
			rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
		}catch (Exception e) {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "조회에 실패하였습니다.");
			e.getStackTrace();
		}
		
		try {
			rtn = om.writeValueAsString(rtnMap);
		} catch (JsonProcessingException e) {
			rtn = "json Mapper Error.";
			e.printStackTrace();
		}
		
		return rtn;
	}
	
	
	/**
	 * @name : LgPlcyHitRgtInsert(로그인정책 적중이력 저장)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 로그인 정책이 적중했을때 해당 정척이 적중한 이력을 저장한다.
	 */
	@ApiOperation(value = "로그인정책 적중이력 저장", notes = "로그인정책 적중이력 저장한다.")
	@PostMapping(path = "/addRegt")
	public String LgPlcyHitRgtInsert(@RequestBody LoginPolicyHitRegisterVo param) throws Exception {

		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		try {
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
	
			//입력값 파라미터 정의
			sqlInpt.put("USRID" 			,param.getUsrId());
			sqlInpt.put("PLCYID" 		,param.getPlcyId());
			sqlInpt.put("LGINIP" 			,param.getLoginIp());
			
			int inputCnt = lgnPlcyHitRegstService.insertLgHitRgst(sqlInpt);
			if(inputCnt > 0) {
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
			}else {
				rtnMap.put("RESULTCD", "1");
				rtnMap.put("RESULTMSG", "등록에 실패 하였습니다.");
			}
		}catch (Exception e) {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "처리중 오류가 발생하였습니다.");
			e.getStackTrace();
		}
		
		rtn = om.writeValueAsString(rtnMap);
		return rtn;
	}

	
	/**
	 * @name : LgPlcyHitRgtUsrDelete(로그인정책 적중이력 삭제)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 사용자 기준으로 로그인 정책 적중 이력을 삭제 한다.
	 */
	@ApiOperation(value = "사용자별 로그인정책 적중이력 초기화", notes = "사용자 단위로 로그인정책 적중이력을 초기화한다.")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "userId", value = "사용자ID", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
	@DeleteMapping(path = "/cleanUsr/{userId}")
	public String LgPlcyHitRgtUsrDelete(@PathVariable("userId") String userId) throws Exception {
		
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		try {
			//입력값 파라미터 정의
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
			sqlInpt.put("USRID", URLDecoder.decode(userId		,"UTF-8"));
			
			int inputCnt = lgnPlcyHitRegstService.deleteLgPlcyHitUsrRgst(sqlInpt);
			if(inputCnt > 0) {
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
			}else {
				rtnMap.put("RESULTCD", "1");
				rtnMap.put("RESULTMSG", "삭제 할 차단대상 IP가 없습니다.");
			}
		}catch (Exception e) {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "처리중 오류가 발생하였습니다.");
			e.getStackTrace();
		}
		rtn = om.writeValueAsString(rtnMap);
		return rtn;
	}

	
	/**
	 * @name : LgPlcyHitRgtPlcyDelete(로그인정책별 로그인정책 적중이력 초기화)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 로그인 정책 기준으로 사용자 적중이력을 삭제 한다. 
	 */
	@ApiOperation(value = "로그인정책별 로그인정책 적중이력 초기화", notes = "로그인정책단위로 로그인정책 적중이력을 초기화한다.")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "plcyId", value = "로그인 정책ID", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
	@DeleteMapping(path = "/cleanPlcy/{PLCYID}")
	public String LgPlcyHitRgtPlcyDelete(@PathVariable("plcyId") String plcyId) throws Exception {
		
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		try {
			//입력값 파라미터 정의
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
			sqlInpt.put("PLCYID", URLDecoder.decode(plcyId		,"UTF-8"));
			
			int inputCnt = lgnPlcyHitRegstService.deleteLgPlcyHitPlcyRgst(sqlInpt);
			if(inputCnt > 0) {
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
			}else {
				rtnMap.put("RESULTCD", "1");
				rtnMap.put("RESULTMSG", "삭제 할 차단대상 IP가 없습니다.");
			}
		}catch (Exception e) {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "처리중 오류가 발생하였습니다.");
			e.getStackTrace();
		}

		rtn = om.writeValueAsString(rtnMap);
		return rtn;
	}

	
	
	/**
	 * @name : LgPlcyHitRgtMsg(로그인 정책 적중이력 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 로그인 정책 적중내용을 조회한다.
	 */
	@ApiOperation(value = "로그인 정책 적중내용 조회")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "userId", value = "사용자ID", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
	@GetMapping(path = "/detailMsg/{userId}")
	public String LgPlcyHitRgtMsg(@PathVariable("userId") String userId) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		try {
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
			sqlInpt.put("USRID", URLDecoder.decode(userId		,"UTF-8"));
			
			rtnMap = lgnPlcyHitRegstService.selectLgPlcyHitlMsg(sqlInpt);
			if(rtnMap==null) {
				rtnMap = new HashMap<String, Object>();
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "이력이 없습니다.");
			}else {
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
			}
		}catch (Exception e) {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "조회에 실패하였습니다.");
			e.getStackTrace();
		}
		
		try {
			rtn = om.writeValueAsString(rtnMap);
		} catch (JsonProcessingException e) {
			rtn = "json Mapper Error.";
			e.printStackTrace();
		}
		
		return rtn;
	}
	

}

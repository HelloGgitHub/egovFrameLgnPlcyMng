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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.lpmg.dao.LoginPolicyManagerService;
import egovframework.com.lpmg.dao.LoginPolicyManagerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "LoginPolicyManagerController", description = "로그인정책 정보 관리 REST API")
@RequestMapping("/lgplcy")
public class LoginPolicyManagerController {
	
	@Autowired
	LoginPolicyManagerService lgnPlcyMngService;
	
	@ApiOperation(value = "로그인정책 목록 조회")
	@GetMapping(path = "/list")
	public String LgPlcyList() {
		
		String rtn = "";
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		ObjectMapper om = new ObjectMapper();

		try {
			List<HashMap<Object, Object>> lst = new ArrayList<HashMap<Object, Object>>();
			lst = lgnPlcyMngService.selectLgPlcyList();
			System.out.println(lst);
			rtnMap.put("list", lst);
			
			rtnMap.put("RESULTCD", "0");
			rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
		}catch (Exception e) {
			e.getStackTrace();
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "조회에 실패하였습니다.");
		}
		
		
		try {
			rtn = om.writeValueAsString(rtnMap);
		} catch (JsonProcessingException e) {
			rtn = "json Mapper Error.";
			e.printStackTrace();
		}
		
		return rtn;
	}
	
	@ApiOperation(value = "로그인정책 정보 조회")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "plcyCd", value = "로그인정책 코드", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
	@GetMapping(path = "/detailInfo/{plcyCd}")
	public String LgPlcyDetailInfo(@PathVariable("plcyCd") String plcyCd) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		try {
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
			sqlInpt.put("PLCYID", URLDecoder.decode(plcyCd		,"UTF-8"));
			
			rtnMap = lgnPlcyMngService.selectLgPlcyDetail(sqlInpt);
			if(rtnMap==null) {
				rtnMap = new HashMap<String, Object>();
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "대상건이 없습니다.");
			}else {
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
			}
			System.out.println(rtnMap);
		}catch (Exception e) {
			e.getStackTrace();
			System.out.println(e);
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "조회에 실패하였습니다.");
		}
		
		try {
			rtn = om.writeValueAsString(rtnMap);
		} catch (JsonProcessingException e) {
			rtn = "json Mapper Error.";
			e.printStackTrace();
		}
		
		return rtn;
	}
	
	
	@ApiOperation(value = "로그인정책 등록", notes = "로그인 정책을 등록 합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !!"),
            @ApiResponse(code = 404, message = "Not Found !!")
    })
	@PostMapping(path = "/addnew")
	public String InsertLgPlcyInfo(@RequestBody LoginPolicyManagerVo param) throws Exception {

		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		try {
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
	
			//입력값 파라미터 정의
			sqlInpt.put("PLCYID" 			,param.getPolicyId());
			sqlInpt.put("PLCYNM" 			,param.getPolicyNm());
			sqlInpt.put("PLCYDC" 			,param.getPolicyDc());
			sqlInpt.put("PLCYBGNDT" 		,param.getPolicyBgndt());
			sqlInpt.put("PLCYENDDT" 		,param.getPolicyEnddt());
			sqlInpt.put("PLCYAPPYYN" 		,param.getPolicyAppyYn());
			sqlInpt.put("PLCYAPPYUSRID" 	,param.getPolicyAppyUsrid());
			
			int rowCnt = lgnPlcyMngService.selectLgPlcyInfoCnt(sqlInpt);
			System.out.println(rowCnt);
			
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
			e.getStackTrace();
			System.out.println(e);
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "처리중 오류가 발생하였습니다.");
		}
		rtn = om.writeValueAsString(rtnMap);
		System.out.println(rtnMap);
		return rtn;
	}

	
	@ApiOperation(value = "로그인 정책 정보수정", notes = "로그인 정책정보를 수정한다.")
	@PutMapping(path = "/modifyInfo")
	public String LgPlcyChangeInfo(@RequestBody LoginPolicyManagerVo param) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		try {
			//입력값 파라미터 정의
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
			sqlInpt.put("PLCYID" 			,param.getPolicyId());
			sqlInpt.put("PLCYNM" 			,param.getPolicyNm());
			sqlInpt.put("PLCYDC" 			,param.getPolicyDc());
			sqlInpt.put("PLCYBGNDT" 		,param.getPolicyBgndt());
			sqlInpt.put("PLCYENDDT" 		,param.getPolicyEnddt());
			sqlInpt.put("PLCYAPPYYN" 		,param.getPolicyAppyYn());
			sqlInpt.put("PLCYAPPYUSRID" 	,param.getPolicyAppyUsrid());
	
			int inputCnt = lgnPlcyMngService.updateLgPlcyInfo(sqlInpt);
			if(inputCnt > 0) {
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
			}else {
				rtnMap.put("RESULTCD", "1");
				rtnMap.put("RESULTMSG", "등록된 로그인정책이 없습니다.");
			}
		}catch (Exception e) {
			e.getStackTrace();
			System.out.println(e);
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "처리중 오류가 발생하였습니다.");
		}
		rtn = om.writeValueAsString(rtnMap);
		System.out.println(rtnMap);
		return rtn;
	}
	
	
	@ApiOperation(value = "로그인정책 삭제", notes = "로그인정책을 삭제한다.")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "plcyCd", value = "로그인정책 코드", required = true, dataType = "string", paramType = "query", defaultValue = "")
    })
	@DeleteMapping(path = "/delete")
	public String LgPlcyDeleteInfo(@RequestParam(value = "plcyCd") String plcyCd) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		try {
			//입력값 파라미터 정의
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
			sqlInpt.put("PLCYID", URLDecoder.decode(plcyCd		,"UTF-8"));
			
			int inputCnt = lgnPlcyMngService.deleteLgPlcyInfo(sqlInpt);
			if(inputCnt > 0) {
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
			}else {
				rtnMap.put("RESULTCD", "1");
				rtnMap.put("RESULTMSG", "삭제 할 로그인 정책이 없습니다.");
			}
		}catch (Exception e) {
			e.getStackTrace();
			System.out.println(e);
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "처리중 오류가 발생하였습니다.");
		}
		rtn = om.writeValueAsString(rtnMap);
		System.out.println(rtnMap);
		return rtn;
	}

	
	@ApiOperation(value = "로그인 적용여부 등록", notes = "로그인 정책의 적용여부를 수정한다.")
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
	
			int inputCnt = lgnPlcyMngService.updateLgPlcyInfo(sqlInpt);
			if(inputCnt > 0) {
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
			}else {
				rtnMap.put("RESULTCD", "1");
				rtnMap.put("RESULTMSG", "등록된 로그인정책이 없습니다.");
			}
		}catch (Exception e) {
			e.getStackTrace();
			System.out.println(e);
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "처리중 오류가 발생하였습니다.");
		}
		rtn = om.writeValueAsString(rtnMap);
		System.out.println(rtnMap);
		return rtn;
	}
}

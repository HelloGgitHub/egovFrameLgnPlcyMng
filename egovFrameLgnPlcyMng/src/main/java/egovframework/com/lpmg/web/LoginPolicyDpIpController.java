package egovframework.com.lpmg.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.lpmg.dao.LoginPolicyDpIpService;
import egovframework.com.lpmg.dao.LoginPolicyDpIpVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "LoginPolicyDpIpController", description = "로그인정책 차단IP 정보 관리 REST APIffffaaaaaa")
@RequestMapping("/lgDpIp")
public class LoginPolicyDpIpController {
	
	@Autowired
	LoginPolicyDpIpService lgnPlcyDpIpService;
	
	@ApiOperation(value = "차단IP 목록 조회")
	@GetMapping(path = "/list")
	public String LgPlcyDpIpList() {
		
		String rtn = "";
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		ObjectMapper om = new ObjectMapper();

		try {
			List<HashMap<Object, Object>> lst = new ArrayList<HashMap<Object, Object>>();
			lst = lgnPlcyDpIpService.selectDpIpList();
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
	
	
	@ApiOperation(value = "차단 IP 등록", notes = "차단대상 IP를 등록 합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !!"),
            @ApiResponse(code = 404, message = "Not Found !!")
    })
	@PostMapping(path = "/addnew")
	public String InsertLgPlcyDpIp(@RequestBody LoginPolicyDpIpVo param) throws Exception {

		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		try {
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
	
			//입력값 파라미터 정의
			sqlInpt.put("BLKIP" 			,param.getBlkIp());
			sqlInpt.put("ADUSR" 			,param.getAddUsrid());
			
			int rowCnt = lgnPlcyDpIpService.selectLgPlcyDpIpCnt(sqlInpt);
			System.out.println(rowCnt);
			
			if(rowCnt == 0) {
				int inputCnt = lgnPlcyDpIpService.insertLgDpIp(sqlInpt);
				if(inputCnt > 0) {
					rtnMap.put("RESULTCD", "0");
					rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
				}else {
					rtnMap.put("RESULTCD", "1");
					rtnMap.put("RESULTMSG", "등록에 실패 하였습니다.");
				}
			}else {
				rtnMap.put("RESULTCD", "1");
				rtnMap.put("RESULTMSG", "중복되는 IP가 있습니다.");
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

	
	
	@ApiOperation(value = "차단 IP 삭제", notes = "차단 대상 IP를 삭제 한다.")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "bkIp", value = "차단IP", required = true, dataType = "string", paramType = "query", defaultValue = "")
    })
	@DeleteMapping(path = "/delete")
	public String LgPlcyDeleteDpIp(@RequestParam(value = "bkIp") String bkIp) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		try {
			//입력값 파라미터 정의
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
			sqlInpt.put("BLKIP", URLDecoder.decode(bkIp		,"UTF-8"));
			
			int inputCnt = lgnPlcyDpIpService.deleteLgPlcyDpIp(sqlInpt);
			if(inputCnt > 0) {
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
			}else {
				rtnMap.put("RESULTCD", "1");
				rtnMap.put("RESULTMSG", "삭제 할 차단대상 IP가 없습니다.");
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

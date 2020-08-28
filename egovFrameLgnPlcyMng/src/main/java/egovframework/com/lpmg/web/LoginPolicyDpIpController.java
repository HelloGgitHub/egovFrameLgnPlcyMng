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

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.ComUtil;
import egovframework.com.lpmg.dao.LoginPolicyDpIpService;
import egovframework.com.lpmg.dao.LoginPolicyDpIpVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @title : 차단IP목록 관리 
 * @package : egovframework.com.lpmg.web
 * @filename : LoginPolicyDpIpController.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 로그인 정책 중 차단IP목록을 관리하는 API모음.
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 15.         "egov"           최초 생성(ver 1.0)
 * 
 */
@RestController
@Api(value = "LoginPolicyDpIpController", description = "로그인정책 차단IP 정보 관리 REST API")
@RequestMapping("/lgDpIp")
public class LoginPolicyDpIpController {
	
	@Autowired
	LoginPolicyDpIpService lgnPlcyDpIpService;
	
	/**
	 * @name : LgPlcyDpIpList(차단IP목록 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @throws Exception 
	 * @return_type : String
	 * @desc : 로그인 정책의 '특정IP접근 차단'에 사용되는 차단IP목록을 조회한다.
	 */
	@ApiOperation(value = "차단IP 목록 조회")
	@GetMapping(path = "/list")
	public String LgPlcyDpIpList() throws Exception {
		
		String rtn = ""; 
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		ObjectMapper om = new ObjectMapper();
		List<HashMap<Object, Object>> lst = new ArrayList<HashMap<Object, Object>>();

		try {
			lst = lgnPlcyDpIpService.selectDpIpList();
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
	 * @name : DpIpDetailInfo(차단IP정보 조회)
	 * @date : 2020. 7. 7.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 차단IP정보를 조회한다..
	 */
	@ApiOperation(value = "차단IP 정보 조회")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "dpIp", value = "차단IP", required = true, dataType = "string", paramType = "query", defaultValue = "")
    	,@ApiImplicitParam(name = "dpIptyp", value = "차단IP TYPE", required = true, dataType = "string", paramType = "query", defaultValue = "")
    })
	@GetMapping(path = "/detailInfo") ///{dpIp}
	public String DpIpDetailInfo(@RequestParam(value="dpIp") String dpIp, @RequestParam(value="dpIptyp") String dpIptyp) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		List<HashMap<Object, Object>> lst = new ArrayList<HashMap<Object, Object>>();
		Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
		
		try {
			sqlInpt.put("BLKIP", 			URLDecoder.decode(dpIp		,"UTF-8"));
			sqlInpt.put("BLKIPTYPE", 	URLDecoder.decode(dpIptyp	,"UTF-8"));
			
			lst = lgnPlcyDpIpService.selectDpIpDetail(sqlInpt);
			
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
	 * @name : InsertLgPlcyDpIp(차단대상 IP등록)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 로그인 정책의 '특정IP접근 차단'에 적용되는 차단IP를 등록한다.
	 */
	@ApiOperation(value = "차단 IP 등록", notes = "차단대상 IP를 등록 합니다.")
	@PostMapping(path = "/addnew")
	public String InsertLgPlcyDpIp(@RequestBody LoginPolicyDpIpVo param) throws Exception {

		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		try {
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
	
			//입력값 파라미터 정의
			sqlInpt.put("BLKIP" 			,param.getBlkIp());
			sqlInpt.put("BLKIPTYP"		,param.getBlkIpTyp());
			sqlInpt.put("BLKIPCIDR"		,param.getBlkIpCidr());
			sqlInpt.put("BLKIPED"		,param.getBlkIpEd());
			sqlInpt.put("BLKNM" 			,param.getBlkIpNm());
			sqlInpt.put("BLKDC" 			,param.getBlkIpDc());
			sqlInpt.put("ADUSR" 			,param.getAddUsrid());
			sqlInpt.put("ADDDT" 			,ComUtil.getTime("yyyyMMddHHmmss"));
			
			int rowCnt = lgnPlcyDpIpService.selectLgPlcyDpIpCnt(sqlInpt);
			
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
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "처리중 오류가 발생하였습니다.");
			e.printStackTrace();
		}

		rtn = om.writeValueAsString(rtnMap);
		return rtn;
	}

	
	
	/**
	 * @name : LgPlcyDeleteDpIp(차단IP 삭제)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 로그인 정책의 '특정IP접근 차단'에 적용되는 차단IP를 삭제한다. 
	 */
	@ApiOperation(value = "차단 IP 삭제", notes = "차단 대상 IP를 삭제 한다.")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "bkIp", value = "차단IP", required = true, dataType = "string", paramType = "query", defaultValue = "")
    })
	@DeleteMapping(path = "/delete")
	public String LgPlcyDeleteDpIp(
			@RequestParam(value = "bkIp") String bkIp
			, @RequestParam(value = "dpIptyp") String dpIptyp ) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		try {
			//입력값 파라미터 정의
			Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
			sqlInpt.put("BLKIP"		, URLDecoder.decode(bkIp		,"UTF-8"));
			sqlInpt.put("BLKIPTYP"	, URLDecoder.decode(dpIptyp		,"UTF-8"));
			
			int inputCnt = lgnPlcyDpIpService.deleteLgPlcyDpIp(sqlInpt);
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
			e.printStackTrace();
		}
		rtn = om.writeValueAsString(rtnMap);
		return rtn;
	}

	
}

package egovframework.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
public class LoginPolicyPageController {
	
	/***********************
	 * 기본
	 ***********************/
	@RequestMapping("/") 
	public String Main(Model model) {
		return "MainPage"; 
	}
	
	@RequestMapping("/FrameDown") 
	public String Main3(Model model) {
		return "FrameDown"; 
	}

	@RequestMapping("/FrameLeft") 
	public String FrameLeft(Model model) {
		return "FrameLeft"; 
	}
	@RequestMapping("/FrameTop") 
	public String FrameTop(Model model) {
		return "FrameTop"; 
	}
	
	
	
	/***********************
	 * 사용자
	 ***********************/
	@RequestMapping("/Login") 
	public String Login(Model model) {
		model.addAttribute("msg", "jstl");
		System.out.println("++++++++++++++++UserInfo++++++++++++");
		return "Login"; 
	}
	
	@RequestMapping("/LoginPolicyList") 
	public String LoginPolicyList(Model model) {
		System.out.println("++++++++++++++++LoginPolicyList.jsp++++++++++++");
		return "/lgp/LoginPolicyList"; 
	}

	@RequestMapping("/LoginPolicyInfo") 
	public String LoginPolicyInfo(Model model
			, @RequestParam(value = "callType") String callType
			, @RequestParam(value = "plcyId") String plcyId) {

		model.addAttribute("callType", 	callType);
		model.addAttribute("plcyId", 	plcyId);

		System.out.println("++++++++++++++++LoginPolicyInfo.jsp++++++++++++");
		return "/lgp/LoginPolicyInfo"; 
	}

	
	@RequestMapping("/DpIPList") 
	public String DpIPList(Model model) {
		System.out.println("++++++++++++++++DpIPList.jsp++++++++++++");
		return "/dip/DpIPList"; 
	}

	@RequestMapping("/DpIPInfo")
	public String DpIPInfo(Model model
			, @RequestParam(value = "callType") String callType
			, @RequestParam(value = "dpIp") String dpIp) {

		model.addAttribute("callType", 	callType);
		model.addAttribute("dpIp", 	dpIp);

		System.out.println("++++++++++++++++DpIPInfo.jsp++++++++++++");
		return "/dip/DpIPInfo"; 
	}

	
	@RequestMapping("/LoginPolicyHitList") 
	public String LoginPolicyHitList(Model model) {
		System.out.println("++++++++++++++++LoginPolicyHitList.jsp++++++++++++");
		return "/hhs/LoginPolicyHitList"; 
	}
	
	@RequestMapping("/LoginPolicyHitDetailList") 
	public String LoginPolicyHitDetailList(Model model
			, @RequestParam(value = "userId") String userId
			, @RequestParam(value = "plcyId") String plcyId) {
		
		model.addAttribute("userId", 	userId);
		model.addAttribute("plcyId", 	plcyId);
		
		System.out.println("++++++++++++++++LoginPolicyHitDetailList.jsp++++++++++++");
		return "/hhs/LoginPolicyHitDetailList"; 
	}

	
}

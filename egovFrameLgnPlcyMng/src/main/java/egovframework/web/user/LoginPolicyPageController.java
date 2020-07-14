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
	public String FrameDown(Model model) {
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
		return "Login"; 
	}
	
	@RequestMapping("/LoginPolicyList") 
	public String LoginPolicyList(Model model) {
		return "/lgp/LoginPolicyList"; 
	}

	@RequestMapping("/LoginPolicyInfo") 
	public String LoginPolicyInfo(Model model
			, @RequestParam(value = "callType") String callType
			, @RequestParam(value = "plcyId") String plcyId) {
		model.addAttribute("callType", 	callType);
		model.addAttribute("plcyId", 	plcyId);
		return "/lgp/LoginPolicyInfo"; 
	}
	
	@RequestMapping("/DpIPList") 
	public String DpIPList(Model model) {
		return "/dip/DpIPList"; 
	}

	@RequestMapping("/DpIPInfo")
	public String DpIPInfo(Model model
			, @RequestParam(value = "callType") String callType
			, @RequestParam(value = "dpIp") String dpIp) {
		model.addAttribute("callType", 	callType);
		model.addAttribute("dpIp", 	dpIp);
		return "/dip/DpIPInfo"; 
	}
	
	@RequestMapping("/LoginPolicyHitList") 
	public String LoginPolicyHitList(Model model) {
		return "/hhs/LoginPolicyHitList"; 
	}
	
	@RequestMapping("/LoginPolicyHitDetailList") 
	public String LoginPolicyHitDetailList(Model model
			, @RequestParam(value = "userId") String userId
			, @RequestParam(value = "plcyId") String plcyId) {
		model.addAttribute("userId", 	userId);
		model.addAttribute("plcyId", 	plcyId);
		return "/hhs/LoginPolicyHitDetailList"; 
	}
	
}

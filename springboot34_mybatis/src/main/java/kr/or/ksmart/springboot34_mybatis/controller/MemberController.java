package kr.or.ksmart.springboot34_mybatis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ksmart.springboot34_mybatis.domain.Member;
import kr.or.ksmart.springboot34_mybatis.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/test")
	public String test(@RequestParam(value="startTime") String startTime
					  ,@RequestParam(value="endTime") String endTime) {
		System.out.println("binding test: " +startTime);
		System.out.println("binding test: " +endTime);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login/login";
	}
	
	@GetMapping("/login")
	public String login(@RequestParam(value="result", required = false) String result
						, Model model) {
		
		model.addAttribute("result", result);
		return "login/login";
	}
	
	@PostMapping("/login")
	public String login( @RequestParam(value="mId", required = false) String mId
						,@RequestParam(value="mPw", required = false) String mPw
						,RedirectAttributes redirectA
						,HttpSession session) {
		System.out.println("binding test mId: " + mId);
		System.out.println("binding test mPw: " + mPw);
		Member member = memberService.selectForUpdate(mId);
		
		//로그인 성공 
		if(member != null && mPw != null && !"".equals(mPw) 
				&& mPw.equals(member.getmPw())) {
			session.setAttribute("SID", member.getmId());
			session.setAttribute("SNAME", member.getmName());
			session.setAttribute("SLEVEL", member.getmLevel());
			return "redirect:/";
		
			//로그인 실패	
		}else {
			redirectA.addAttribute("result", "등록된 회원이 아닙니다.");			
			return "redirect:/login";
		}
	}
	
	@PostMapping("/memberList")
	public String memberList(@RequestParam(value="sk") String sk
							,@RequestParam(value="sv", required = false) String sv
							,Model model) {
		System.out.println("binding test sk: " + sk);
		System.out.println("binding test sv: " + sv);
		List<Member> list = memberService.getSearchMemberList(sk, sv);
		model.addAttribute("memberList", list);
		return "mlist/memberList";
	}
	
	@GetMapping("/mDelete")
	public String mDelete(@RequestParam(value="mId") String mId
							, Model model) {
		model.addAttribute("mId", mId);
		return "mDelete/mDelete";
	}
	
	@PostMapping("/mDelete")
	public String mDelete(@RequestParam(value="mId") String mId
						 ,@RequestParam(value="mPw", required = false) String mPw
						 ,RedirectAttributes redirectA) {
		Member member = memberService.selectForUpdate(mId);
		if(mPw != null && !"".equals(mPw) 
				&& mPw.equals(member.getmPw())) {
			memberService.mDelete(mId, mPw);
			return "redirect:/memberList";
			
		}else {
			redirectA.addAttribute("mId", mId);			
			return "redirect:/mDelete";
		}
	}
	
	@PostMapping("/mUpdate")
	public String mUpdate(Member member) {
		System.out.println(member.toString());
		int result = memberService.mUpdate(member);
		
		if(result > 0) {
			return "redirect:/memberList";
		}
		
		return "redirect:/mUpdate";
	}
	
	@GetMapping("/mUpdate")
	public String mUpdate(@RequestParam(value="mId") String mId
						 , Model model) {
		System.out.println("binding test:" + mId);
		System.out.println("binding test2: " + 
						memberService.selectForUpdate(mId).toString());
		model.addAttribute("Member", memberService.selectForUpdate(mId));
		return "mUpdate/mUpdate";
	}
	
	@GetMapping("/memberList")
	public String getMemberList(Model model) {
		List<Member> list = memberService.getMemberList();
		model.addAttribute("memberList", list);
		//System.out.println(memberService.getMemberList().toString());
		
		return "mlist/memberList";
	}
	
	//회원 가입 폼 
	@GetMapping("/mInsert")
	public String mInsert() {
		
		return "mInsert/mInsert";
	}
	
	@PostMapping("/mInsert")
	public String mInsert(Member member) {
		System.out.println(member.toString());
		int result = memberService.mInsert(member);
		if(result > 0) {
			return "redirect:/memberList";
		}
			
		return "redirect:/mInsert";
	}
	
	

	
	
	
	
	
	
}

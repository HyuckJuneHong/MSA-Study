package kr.co.memberservice.controller;

import kr.co.memberservice.domain.MemberDto;
import kr.co.memberservice.domain.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actuator/info")  //gateway routing을 위해
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public String test(){
        return "member-test";
    }

    @PostMapping("/signUp")
    public String signUp(@RequestBody MemberDto.CREATE create) {
        memberService.signUp(create);
        return create.getName() + "님 회원가입 축하드립니다.";
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberDto.LOGIN login){
        return memberService.login(login);
    }
}

package kr.co.memberservice.controller;

import kr.co.memberservice.domain.MemberDto;
import kr.co.memberservice.domain.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/actuator/info/member")  //gateway routing을 위해
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping()
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

    @GetMapping("/read")
    public Map<String, String> getMembers(@RequestParam("identity") String identity){
        MemberDto.READ read = memberService.getMembers(identity);
        Map<String, String> map = new HashMap<>();
        map.put("identity", read.getIdentity());
        map.put("name", read.getName());
        return map;
    }

    @GetMapping("/read/boards")
    public Map<String, String> getBoards(@RequestParam("identity") String identity){
        MemberDto.READ_BOARD read_board = memberService.getBoards(identity);
        Map<String, String> map = new HashMap<>();
        map.put("title", read_board.getTitle());
        map.put("content", read_board.getContent());
        map.put("identity", read_board.getIdentity());

        return map;
    }
}

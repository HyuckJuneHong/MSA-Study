package kr.co.memberservice.domain;

import kr.co.memberservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signUp(MemberDto.CREATE create){
        MemberEntity memberEntity = MemberEntity.builder()
                .identity(create.getIdentity())
                .password(create.getPassword())
                .name(create.getName())
                .build();

        memberRepository.save(memberEntity);
    }

    public String login(MemberDto.LOGIN login){

        MemberEntity memberEntity = memberRepository.findByIdentity(login.getIdentity());

        if(login.getPassword().equals(memberEntity.getPassword())){
            return memberEntity.getName() + " 로그인 성공";
        }else{
            return "로그인 실패";
        }
    }
}

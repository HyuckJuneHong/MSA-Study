package kr.co.memberservice.domain;

import kr.co.memberservice.interfaces.BoardFeignClient;
import kr.co.memberservice.interfaces.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BoardFeignClient boardFeignClient;

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

    public MemberDto.READ getMembers(String identity){
        MemberEntity memberEntity = memberRepository.findByIdentity(identity);

        MemberDto.READ read = MemberDto.READ.builder()
                .identity(memberEntity.getIdentity())
                .name(memberEntity.getName())
                .build();

        return read;
    }

    public MemberDto.READ_BOARD getBoards(String identity){

        MemberEntity memberEntity = memberRepository.findByIdentity(identity);
        Map<String, String> map = boardFeignClient.getBoards(memberEntity.getIdentity());


        MemberDto.READ_BOARD read_board = MemberDto.READ_BOARD.builder()
                .title(map.get("title"))
                .content(map.get("content"))
                .identity(memberEntity.getIdentity())
                .build();

        return read_board;
    }
}

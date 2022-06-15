package kr.co.boardservice.controller;

import kr.co.boardservice.domain.BoardDto;
import kr.co.boardservice.domain.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/actuator/info/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping()
    public String test(){
        return "board-test";
    }

    @PostMapping("/create")
    public String boardCreate(@RequestBody BoardDto.CREATE create){
        boardService.boardCreate(create);

        return create.getIdentity() + "님 게시글 작성이 완료 되었습니다.";
    }

    @GetMapping("/read")
    public Map<String, String> getBoards(@RequestParam("identity") String identity){
        BoardDto.READ read = boardService.getBoards(identity);

        Map<String, String> map = new HashMap<>();
        map.put("title", read.getTitle());
        map.put("content", read.getContent());
        map.put("identity", read.getIdentity());

        return map;
    }


}

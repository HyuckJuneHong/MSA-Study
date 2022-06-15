package kr.co.boardservice.domain;

import kr.co.boardservice.interfaces.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void boardCreate(BoardDto.CREATE create){

        BoardEntity boardEntity = BoardEntity.builder()
                .title(create.getTitle())
                .content(create.getContent())
                .identity(create.getIdentity())
                .build();

        boardRepository.save(boardEntity);
    }

    public BoardDto.READ getBoards(String identity){

        BoardEntity boardEntity = boardRepository.findByIdentity(identity);
        BoardDto.READ read = BoardDto.READ.builder()
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .identity(boardEntity.getIdentity())
                .build();

        return read;
    }
}

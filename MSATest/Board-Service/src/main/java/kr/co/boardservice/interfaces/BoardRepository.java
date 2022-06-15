package kr.co.boardservice.interfaces;

import kr.co.boardservice.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    BoardEntity findByIdentity(String identity);
}

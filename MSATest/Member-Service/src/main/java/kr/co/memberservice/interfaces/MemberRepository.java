package kr.co.memberservice.interfaces;

import kr.co.memberservice.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByIdentity(String identity);
}

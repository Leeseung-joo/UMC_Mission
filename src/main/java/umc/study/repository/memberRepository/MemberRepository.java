package umc.study.repository.memberRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(Long id);
    boolean existsById(Long id);
}

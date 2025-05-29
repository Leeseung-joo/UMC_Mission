package umc.study.repository.missionHistoryRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import umc.study.domain.Member;
import umc.study.domain.Status;
import umc.study.mapping.MissionHistory;

public interface MissionHistoryRepository extends JpaRepository<MissionHistory,Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM MissionHistory mh WHERE mh.member = :member")
    void deleteByMember(Member member);

    List<MissionHistory> findAllByMemberId(Long memberId);
    Page<MissionHistory> findAllByMemberIdAndStatus(Long memberId, Status status, PageRequest pageRequest);
    List<MissionHistory> findAllByMemberAndStatus(Member member, Status status);
}

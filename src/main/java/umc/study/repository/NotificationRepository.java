package umc.study.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import umc.study.domain.Member;
import umc.study.domain.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Notification n where n.member = :member")
    void deleteByMember(Member member);
}

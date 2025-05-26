package umc.study.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Member;
import umc.study.repository.memberRepository.MemberRepository;
import umc.study.repository.missionHistoryRepository.MissionHistoryRepository;
import umc.study.repository.NotificationRepository;

@Service
@RequiredArgsConstructor

public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionHistoryRepository missionHistoryRepository;
    private final NotificationRepository notificationRepository;

    @Transactional
    public void deleteMember(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다. ID=" + memberId));

        missionHistoryRepository.deleteByMember(member);

        notificationRepository.deleteByMember(member);

        //나머지는 orphanRemoval = true여서 자동 삭제

        memberRepository.delete(member);
    }

}

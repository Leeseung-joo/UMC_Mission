package umc.study.service.missionService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.apiPayload.exception.handler.MemberHandler;
import umc.study.apiPayload.exception.handler.MissionHandler;
import umc.study.converter.MissionHistoryConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Status;
import umc.study.mapping.MissionHistory;
import umc.study.repository.memberRepository.MemberRepository;
import umc.study.repository.missionHistoryRepository.MissionHistoryRepository;
import umc.study.repository.missionRepository.MissionRepository;
import umc.study.web.request.CreateMissionHistoryRequest;
import umc.study.web.response.CreateMissionHistoryResponse;

@Service
@RequiredArgsConstructor
public class MissionHistoryCommandServiceImpl implements MissionHistoryCommandService {
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MissionHistoryRepository missionHistoryRepository;

    @Transactional
    public CreateMissionHistoryResponse createMissionHistory(CreateMissionHistoryRequest request) {
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        MissionHistory missionHistory = MissionHistoryConverter.toMissionHistory(request, mission, member);
        MissionHistory saved = missionHistoryRepository.save(missionHistory);

        return new CreateMissionHistoryResponse(saved.getId(), "미션이 진행 중으로 등록되었습니다.");
    }

    @Transactional
    public void updateMissionStatus(Long memberId, Status status){
        //내 진행중인 미션 가져오고, 그 진행중인 것을 진행완료로 수정해야함
        Member member = memberRepository.findById(memberId).get();

        List<MissionHistory> inProgressList = missionHistoryRepository
                .findAllByMemberAndStatus(member, Status.IN_PROGRESS);

        if (inProgressList.isEmpty()) {
            throw new GeneralException(ErrorStatus.IN_PROGRESS_MISSION_NOT_FOUND);
        }
        inProgressList.forEach(mh -> mh.changeStatus(Status.COMPLETED));

    }
}

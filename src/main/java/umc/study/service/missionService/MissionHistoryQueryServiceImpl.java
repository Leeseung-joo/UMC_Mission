package umc.study.service.missionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.study.converter.MemberConverter;
import umc.study.domain.Member;
import umc.study.mapping.MissionHistory;
import umc.study.repository.memberRepository.MemberRepository;
import umc.study.repository.missionHistoryRepository.MissionHistoryRepository;
import umc.study.web.response.MissionInProgressResponseDTO;

@Service
@RequiredArgsConstructor
public class MissionHistoryQueryServiceImpl implements MissionHistoryQueryService {

    private final MissionHistoryRepository missionHistoryRepository;

    public MissionInProgressResponseDTO getMissionInProgressList(Long memberId, String status, Integer page) {

        Page<MissionHistory> inProgressMissionPage = missionHistoryRepository.findAllByMemberIdAndStatus(memberId,status, PageRequest.of(page, 10));
        return MemberConverter.toMissionInProgressResponseDTO(inProgressMissionPage);
    }

}

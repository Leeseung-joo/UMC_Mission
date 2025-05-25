package umc.study.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.converter.MemberMissionConverter;
import umc.study.mapping.MissionHistory;
import umc.study.repository.missionHistoryRepository.MissionHistoryRepository;
import umc.study.web.response.MemberMissionResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionService {

    private final MissionHistoryRepository missionHistoryRepository;

    public List<MemberMissionResponse> getMemberMissions(Long memberId){
        List<MissionHistory> missionHistories = missionHistoryRepository.findAllByMemberId(memberId);
        return missionHistories.stream()
                .map(MemberMissionConverter::fromMissionHistory)
                .toList();
    }
}

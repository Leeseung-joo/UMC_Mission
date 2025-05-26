package umc.study.service.missionService;

import org.springframework.data.domain.Page;
import umc.study.mapping.MissionHistory;
import umc.study.web.response.MissionInProgressResponseDTO;

public interface MissionHistoryQueryService {
    MissionInProgressResponseDTO getMissionInProgressList(Long memberId, String status, Integer page);
}


package umc.study.service.missionService;

import umc.study.domain.Status;
import umc.study.web.request.CreateMissionHistoryRequest;
import umc.study.web.response.CreateMissionHistoryResponse;

public interface MissionHistoryCommandService {
    CreateMissionHistoryResponse createMissionHistory(CreateMissionHistoryRequest request);
    void updateMissionStatus(Long id, Status status);
}

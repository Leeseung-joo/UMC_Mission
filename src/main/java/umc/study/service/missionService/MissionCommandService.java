package umc.study.service.missionService;

import umc.study.domain.Status;
import umc.study.web.request.CreateMissionRequest;
import umc.study.web.response.CreateMissionResponse;

public interface MissionCommandService {
    CreateMissionResponse createMission(Long id, CreateMissionRequest request);

}

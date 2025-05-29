package umc.study.service.missionService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.GeneralException;
import umc.study.apiPayload.exception.handler.RestaurantHandler;
import umc.study.converter.MissionConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Restaurant;
import umc.study.domain.Status;
import umc.study.mapping.MissionHistory;
import umc.study.repository.memberRepository.MemberRepository;
import umc.study.repository.missionHistoryRepository.MissionHistoryRepository;
import umc.study.repository.missionRepository.MissionRepository;
import umc.study.repository.restaurantRepository.RestaurantRepository;
import umc.study.web.request.CreateMissionRequest;
import umc.study.web.response.CreateMissionResponse;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {


    private final RestaurantRepository restaurantRepository;
    private final MissionRepository missionRepository;
    private final MissionHistoryRepository missionHistoryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CreateMissionResponse createMission(Long restaurantId, CreateMissionRequest request) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        Mission mission = MissionConverter.toMission(request, restaurant);

        Mission savedMission = missionRepository.save(mission);

        return new CreateMissionResponse(savedMission.getId(), "미션이 성공적으로 등록되었습니다.");
    }


}



package umc.study.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import umc.study.domain.Food;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.mapping.FavoriteFood;
import umc.study.mapping.MissionHistory;
import umc.study.web.request.JoinDTO;
import umc.study.web.response.JoinResultDTO;
import umc.study.web.response.MissionInProgressResponseDTO;
import umc.study.web.response.MissionInProgressResponseDTO.MissionInProgressDTO;

public class MemberConverter {

    public static JoinResultDTO toJoinResultDTO(Member member) {
        return JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(JoinDTO request){
        return Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .gender(request.getGender())
                .birthdate(request.getBirthdate())
                .location(request.getLocation())
                .favoriteFoodList(new ArrayList<>())
                .build();
    }

    public static List<FavoriteFood> toMemberFavoriteFood(List<Food> foodList) {
            return foodList.stream()
                    .map(food -> FavoriteFood.builder()
                            .food(food)
                            .build())
                    .collect(Collectors.toList());
        }

    public static MissionInProgressResponseDTO.MissionInProgressDTO toMissionInProgressDTO(MissionHistory mh) {
        Mission mission = mh.getMission();
        return MissionInProgressResponseDTO.MissionInProgressDTO.builder()
                .missionId(mission.getId())
                .missionTitle(mission.getTitle())
                .reward(mission.getReward())
                .requiredAmount(mission.getRequiredAmount())
                .build();
    }



    public static MissionInProgressResponseDTO toMissionInProgressResponseDTO(Page<MissionHistory> missionList) {
        List<MissionInProgressDTO> missionInProgressDTOList = missionList.stream()
                .map(MemberConverter::toMissionInProgressDTO).collect(Collectors.toList());
        return MissionInProgressResponseDTO.builder()
                .missionInProgressList(missionInProgressDTOList)
                .listSize(missionInProgressDTOList.size())
                .totalPage(missionList.getTotalPages())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .totalElements(missionList.getTotalElements())
                .build();
    }

}


    //엔티티를 응답 dto로 변환
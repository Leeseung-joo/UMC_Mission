package umc.study.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import umc.study.domain.Food;
import umc.study.domain.Member;
import umc.study.mapping.FavoriteFood;
import umc.study.web.dto.JoinDTO;
import umc.study.web.dto.JoinResultDTO;

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


}

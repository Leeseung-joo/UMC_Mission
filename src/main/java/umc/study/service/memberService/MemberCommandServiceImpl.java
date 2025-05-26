package umc.study.service.memberService;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.study.converter.MemberConverter;
import umc.study.domain.Food;
import umc.study.domain.Member;
import umc.study.mapping.FavoriteFood;
import umc.study.repository.FoodRepository;
import umc.study.repository.memberRepository.MemberRepository;
import umc.study.web.request.JoinDTO;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;

    @Override
    public Member joinMember(JoinDTO request){
        Member newMember = MemberConverter.toMember(request);
        List<Food> foodList = request.getPreferFood().stream()
                .map(food ->{
                    return foodRepository.findById(food).orElseThrow(() -> new FoodCategoryHandler(
                            ErrorStatus.FOOD_CATEGORY_NOT_FOUND));

                }).collect(Collectors.toList());
        List<FavoriteFood> memberFavoriteFood = MemberConverter.toMemberFavoriteFood(foodList);
        memberFavoriteFood.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});
        return memberRepository.save(newMember);
    }
}

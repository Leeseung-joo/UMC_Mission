package umc.study.service.memberService;

import umc.study.web.response.MyReviewResponseDTO;

public interface MemberQueryService {
    MyReviewResponseDTO getReviewList(Long memberId, Integer page);
}

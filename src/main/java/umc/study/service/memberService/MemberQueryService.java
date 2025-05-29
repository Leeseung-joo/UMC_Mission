package umc.study.service.memberService;

import jakarta.servlet.http.HttpServletRequest;
import umc.study.web.request.MemberInfoDTO;
import umc.study.web.response.MyReviewResponseDTO;

public interface MemberQueryService {
    MyReviewResponseDTO getReviewList(Long memberId, Integer page);
    MemberInfoDTO getMemberInfo(HttpServletRequest request);
}

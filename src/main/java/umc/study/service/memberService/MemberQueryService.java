package umc.study.service.memberService;

import java.util.List;
import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.web.dto.MyReviewResponseDTO;

public interface MemberQueryService {
    MyReviewResponseDTO getReviewList(Long memberId, Integer page);
}

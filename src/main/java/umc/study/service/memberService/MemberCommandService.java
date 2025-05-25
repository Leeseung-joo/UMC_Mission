package umc.study.service.memberService;

import java.util.List;
import umc.study.domain.Member;
import umc.study.web.dto.JoinDTO;
import umc.study.web.dto.MyReviewResponseDTO;

public interface MemberCommandService {
    Member joinMember(JoinDTO request);

}

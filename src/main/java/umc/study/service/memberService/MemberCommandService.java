package umc.study.service.memberService;

import umc.study.domain.Member;
import umc.study.web.request.JoinDTO;

public interface MemberCommandService {
    Member joinMember(JoinDTO request);

}

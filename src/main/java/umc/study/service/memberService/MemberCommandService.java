package umc.study.service.memberService;

import umc.study.domain.Member;
import umc.study.web.request.JoinDTO;
import umc.study.web.request.LoginRequestDTO;
import umc.study.web.response.LoginResultDTO;

public interface MemberCommandService {
    Member joinMember(JoinDTO request);
    LoginResultDTO loginMember(LoginRequestDTO request);
}

package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.domain.Member;
import umc.study.service.MemberService;
import umc.study.service.memberService.MemberCommandService;
import umc.study.service.memberService.MemberQueryService;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ValidPage;
import umc.study.web.dto.JoinDTO;
import umc.study.web.dto.JoinResultDTO;
import umc.study.web.dto.MyReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
@Validated
public class MemberController {

    private final MemberService memberService;
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    @DeleteMapping("/{member-id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ApiResponse<JoinResultDTO> join(@RequestBody @Valid JoinDTO request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("/{member-id}/reviews")
    @Operation(summary = "나의 리뷰 목록 조회 API", description = "내가 작성한 리뷰들의 목록을 조회하는 API이며 페이징을 포함합니다. query String으로 Page번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })     @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!")
    })
    public  ApiResponse<MyReviewResponseDTO> getReviewList(
            @RequestParam (name = "page") @ValidPage Integer page,
            @PathVariable (name = "member-id") @ExistMember Long memberId){

        int zeroBasedPage = page - 1;   //1페이지로 전달된 값을 0으로 처리하는 커스텀 어노테이션을 못 만들겠음, 컨트롤러에서 처리함
        MyReviewResponseDTO result = memberQueryService.getReviewList(memberId,zeroBasedPage);
        return ApiResponse.onSuccess(result);

    }
}



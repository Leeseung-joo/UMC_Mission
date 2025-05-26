package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.domain.Status;
import umc.study.service.MemberMissionService;
import umc.study.service.missionService.MissionHistoryCommandService;
import umc.study.service.missionService.MissionHistoryQueryService;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ValidMissionStatus;
import umc.study.validation.annotation.ValidPage;
import umc.study.web.response.MemberMissionResponse;
import umc.study.web.response.MissionInProgressResponseDTO;
import umc.study.web.response.MissionInProgressResponseDTO.MissionInProgressDTO;
import umc.study.web.response.MyReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
@Validated
public class MemberMissionController {

    private final MemberMissionService memberMissionService;
    private final MissionHistoryQueryService missionHistoryQueryService;
    private final MissionHistoryCommandService missionHistoryCommandService;

    @GetMapping("/{member-id}/mission")
    public ResponseEntity<List<MemberMissionResponse>> getMemberMissions(@PathVariable("member-id") Long memberId){
        List<MemberMissionResponse> MemberMissionResponse = memberMissionService.getMemberMissions(memberId);
        return ResponseEntity.ok(MemberMissionResponse);
    }

    @GetMapping("/{member-id}/missions")
    @Operation(summary = "나의 진행중인 미션 목록 조회 API", description = "내가 진행중인 미션 목록을 조회하는 API이며 페이징을 포함함")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })     @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "status", description = "미션의 상태(진행중), request param입니다!")
    })
    public ApiResponse<MissionInProgressResponseDTO> getMissionsInProgress(
            @ExistMember @PathVariable("member-id") Long memberId,
            @RequestParam(name = "status") @ValidMissionStatus String statusString,
            @RequestParam (name = "page") @ValidPage Integer page){

        int zeroBasedPage = page - 1;
        Status status = Status.valueOf(statusString.toUpperCase());
        MissionInProgressResponseDTO result = missionHistoryQueryService.getMissionInProgressList(memberId,status,zeroBasedPage);
                return ApiResponse.onSuccess(result);
            }

    @PatchMapping("/{member-id}/missions")
    @Operation(summary = "진행 중인 미션을 진행 완료로 변경하는 API", description = "진행 중인 미션을 진행 완료로 변경하는 API이며 페이징을 포함합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })     @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "status", description = "미션의 진행상태(완료), request param입니다!")
    })
    public ApiResponse<Void> updateMissionStatus(
            @ExistMember @PathVariable("member-id") Long memberId,
            @RequestParam(name = "status") @ValidMissionStatus String statusString){

        Status status = Status.valueOf(statusString.toUpperCase());
        missionHistoryCommandService.updateMissionStatus(memberId,status);
        return ApiResponse.onSuccess(null);
    }



}

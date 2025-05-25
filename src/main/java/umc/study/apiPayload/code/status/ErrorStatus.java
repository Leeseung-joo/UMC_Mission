package umc.study.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "Food400", "좋아하는 음식이 없습니다."),

    // 멤버 관련 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),

    //아티클 관련 에러
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),
    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),

    //디스코드
    DISCORD_EXCEPTION(HttpStatus.BAD_REQUEST, "DISCORD4001", "테스트용"),

    RESTAURANT_NOT_FOUND(HttpStatus.BAD_REQUEST,"RESTAURANT4001", "레스토랑이 없습니다."),

    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST,"MISSION4001",  "미션이 없습니다."),

    //인증관련
    AUTH_TOKEN_REQUIRED(HttpStatus.UNAUTHORIZED, "AUTH003", "access 토큰을 주세요!"),
    AUTH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "AUTH004", "access 토큰 만료"),
    AUTH_TOKEN_INVALID_FORMAT(HttpStatus.UNAUTHORIZED, "AUTH006", "access 토큰 모양이 이상함"),
    //페이지 관련
    INVALID_PAGE_NUMBER(HttpStatus.BAD_REQUEST, "PAGE4001", "page 값은 1 이상이어야 합니다.");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}

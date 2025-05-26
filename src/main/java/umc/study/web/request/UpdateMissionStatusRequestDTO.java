package umc.study.web.request;

import lombok.Getter;
import umc.study.validation.annotation.ValidMissionStatus;

@Getter
public class UpdateMissionStatusRequestDTO {

    @ValidMissionStatus
    private String status;
}

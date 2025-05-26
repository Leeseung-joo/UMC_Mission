package umc.study.web.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MissionInProgressResponseDTO {

    private List<MissionInProgressDTO> missionInProgressList;
    private Integer listSize;
    private Integer totalPage;
    private Long totalElements;
    private Boolean isFirst;
    private Boolean isLast;





    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class MissionInProgressDTO{
        private Long missionId;
        private String missionTitle;
        private Long reward;
        private BigDecimal requiredAmount;
    }

}

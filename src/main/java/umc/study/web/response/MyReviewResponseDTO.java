package umc.study.web.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MyReviewResponseDTO {
    private List<ReviewDTO> reviews;
    private Integer listSize;
    private Integer totalPage;
    private Long totalElements;
    private Boolean isFirst;
    private Boolean isLast;



    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class ReviewDTO{
        private double starPoints;
        private String content;
        private LocalDateTime createdAt;
    }

}

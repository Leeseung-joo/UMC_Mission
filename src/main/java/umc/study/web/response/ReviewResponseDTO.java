package umc.study.web.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.domain.ReviewImage;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {
    private Long id;
    private Long restuarantId;
    private String content;
    private double rating;
    private List<ReviewImage> reviewImageList;


}

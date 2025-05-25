package umc.study.web.dto;

import java.util.List;
import lombok.Getter;
import umc.study.domain.ReviewImage;

@Getter
public class ReviewRequestDTO {
    private Long id;
    private String content;
    private double rating;
    private List<ReviewImage> reviewImageList;
}

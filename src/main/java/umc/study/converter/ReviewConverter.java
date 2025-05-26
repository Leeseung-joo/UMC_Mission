package umc.study.converter;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.web.response.MyReviewResponseDTO;

public class ReviewConverter {
    public static MyReviewResponseDTO.ReviewDTO toReviewDTO(Review review) {
        return MyReviewResponseDTO.ReviewDTO.builder()
                .starPoints(review.getStarPoints())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static MyReviewResponseDTO toMyReviewResponseDTO(Page<Review> reviewList){
        List<MyReviewResponseDTO.ReviewDTO> reviewDTOList = reviewList.stream()
                .map(ReviewConverter::toReviewDTO).collect(Collectors.toList());
        return MyReviewResponseDTO.builder()
                .reviews(reviewDTOList)
                .listSize(reviewDTOList.size())
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .build();
    }
}

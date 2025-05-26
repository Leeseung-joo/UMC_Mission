package umc.study.converter;

import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.web.request.ReviewRequestDTO;
import umc.study.web.response.ReviewResponseDTO;

public class RestuarantConverter {

    public static ReviewResponseDTO toReviewResponseDTO(Review review) {
        return ReviewResponseDTO.builder()
                .id(review.getId())
                .restuarantId(review.getRestaurant().getId())
                .content(review.getContent())
                .rating(review.getStarPoints())
                .reviewImageList(review.getReviewImageList())
                .build();
    }
    public static Review toReview(ReviewRequestDTO request, Restaurant restaurant) {
        return Review.builder()
                .id(request.getId())
                .restaurant(restaurant)
                .content(request.getContent())
                .starPoints(request.getRating())
                .reviewImageList(request.getReviewImageList())
                .build();
    }
}

package umc.study.service.restaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.RestaurantHandler;
import umc.study.converter.RestuarantConverter;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.repository.restaurantRepository.RestaurantRepository;
import umc.study.repository.reviewRepository.ReviewRepository;
import umc.study.web.request.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class RestuarantCommandServiceImpl implements RestuarantCommandService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public Review createReview(ReviewRequestDTO request, Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantHandler(
                ErrorStatus.RESTAURANT_NOT_FOUND));
        Review review = RestuarantConverter.toReview(request, restaurant);
        reviewRepository.save(review);
        return review;
    }
}

package umc.study.service.restaurantService;

import umc.study.domain.Review;
import umc.study.web.request.ReviewRequestDTO;

public interface RestuarantCommandService {
    Review createReview(ReviewRequestDTO request, Long id);
}

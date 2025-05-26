package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.RestuarantConverter;
import umc.study.domain.Review;
import umc.study.service.restaurantService.RestuarantCommandService;
import umc.study.web.request.ReviewRequestDTO;
import umc.study.web.response.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/restaurants")
public class RestaurantController {

    private final RestuarantCommandService restuarantCommandService;

    @PostMapping("{restuarant-id}/reviews")
    public ApiResponse<ReviewResponseDTO> createReview(
            @PathVariable Long restuarantId,
            @RequestBody @Valid ReviewRequestDTO request
    ){
        Review review = restuarantCommandService.createReview(request, restuarantId);
        return ApiResponse.onSuccess(RestuarantConverter.toReviewResponseDTO(review));
    }

}

package umc.study.service.memberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.repository.memberRepository.MemberRepository;
import umc.study.repository.reviewRepository.ReviewRepository;
import umc.study.web.response.MyReviewResponseDTO;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public MyReviewResponseDTO getReviewList(Long memberId, Integer page){

        Member member = memberRepository.findById(memberId).get();
        Page<Review> reviewPage = reviewRepository.findAllByMember(member, PageRequest.of(page,10));
        return ReviewConverter.toMyReviewResponseDTO(reviewPage);
    }
}

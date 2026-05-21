package com.hritik.reviewms.reviews.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hritik.reviewms.reviews.Review;
import com.hritik.reviewms.reviews.ReviewRepository;
import com.hritik.reviewms.reviews.ReviewService;

@Service
public class ReviewServiceImplementation implements ReviewService {
    
    private ReviewRepository reviewRepository;
    // private CompanyService companyService;

    public ReviewServiceImplementation(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        // this.companyService = companyService;
    }

    

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review){
        if(companyId != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
        
    }

    @Override
    public Review getReviewById(Long reviewId){

        // works well but does not verify if the review belongs to that specific company or not
        //return reviewRepository.findById(reviewId).orElse(null);
        
        // best method - but we need to keep return type as Optional<Review> and not Review
        // Review review = reviewRepository.findByIdAndCompanyId(reviewId, companyId).orElse(null);

        // List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        // return reviews.stream().
        //        filter(review -> review.getId().equals(reviewId))
        //        .findFirst()
        //        .orElse(null);

        return reviewRepository.findById(reviewId).orElse(null);
    }



    @Override
    public boolean updateReview(Long reviewId, Review review) {
        Review rev = reviewRepository.findById(reviewId).orElse(review);

        if(rev != null){
            // it is not necessary that the review contains the company ID, so we need not update it
            // rev.setCompanyId(review.getCompanyId());
            rev.setDescription(review.getDescription());
            rev.setTitle(review.getTitle());
            rev.setRating(review.getRating());
            reviewRepository.save(rev);
            return true;
        }

        return false;
    }



    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);

        if(review != null){
            reviewRepository.deleteById(reviewId);
            return true;

            // MY SOLUTION
            // Review review = reviewRepository
            //                 .findByCompanyId(companyId)
            //                 .stream()
            //                 .filter(rev -> rev.getId().equals(reviewId))
            //                 .findFirst()
            //                 .orElse(null);
            
            // if(review != null){
            //     reviewRepository.delete(review);
            //     return true;
            // }
            


            // BEST APPROACH BY GPT
            // Review review = reviewRepository
            //                 .findByIdAndCompanyId(reviewId, companyId)
            //                 .orElse(null);

            // if (review != null) {
            //     reviewRepository.delete(review);
            //     return true;
            // }
            // return false;



            // FAISAL SOLUTION
            // if(reviewRepository.existsById(reviewId)){
            //     Review review = reviewRepository.findById(reviewId).orElse(null);
            //     Company comp = review.getCompany();
            //     comp.getReviews().remove(review);  // for in-memory consistency, not DB
            //     companyService.updateCompany(comp, companyId);
            //     reviewRepository.deleteById(reviewId);
            //     return true;
            // }
        }
        return false;
    }

    
}

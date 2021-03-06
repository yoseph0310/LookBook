package com.pjt2.lb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pjt2.lb.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	Review findById(int reviewId);
	
	Review findFirstByOrderByReviewLikeCntDesc();
}

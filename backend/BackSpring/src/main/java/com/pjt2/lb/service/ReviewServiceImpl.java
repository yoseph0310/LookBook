package com.pjt2.lb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.dao.ReviewDao;
import com.pjt2.lb.entity.Book;
import com.pjt2.lb.entity.Review;
import com.pjt2.lb.repository.BookGradeRepositorySupport;
import com.pjt2.lb.repository.BookRepository;
import com.pjt2.lb.repository.ReviewRepository;
import com.pjt2.lb.repository.UserRepository;
import com.pjt2.lb.response.UserReviewListInfoRes;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	BookGradeRepositorySupport bookGradeRepositorySupport;
	
	@Autowired
	ReviewDao reviewDao;
	
	@Override
	public int insertReview(String userEmail, String bookIsbn, String reviewContent) {
		try {
			Review review = new Review();
			review.setBook(bookRepository.findByBookIsbn(bookIsbn));
			review.setUser(userRepository.findUserByUserEmail(userEmail));
			review.setReviewContent(reviewContent);
			reviewRepository.save(review);
			
			Book book = bookRepository.findByBookIsbn(bookIsbn);
			book.setBookLikeCnt(book.getBookLikeCnt()+1);
			bookRepository.save(book);
			
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public int deleteReview(int reviewId) {
		try {
			Review review = reviewRepository.findById(reviewId);
			Book book = bookRepository.findByBookIsbn(review.getBook().getBookIsbn());
			book.setBookLikeCnt(book.getBookLikeCnt()-1);
			bookRepository.save(book);
			
			reviewRepository.deleteById(reviewId);
			
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public int updateReview(int reviewId, String bookIsbn, String reviewContent) {
		try {
			Review review = reviewRepository.findById(reviewId);
			review.setReviewContent(reviewContent);
			reviewRepository.save(review);
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<UserReviewListInfoRes> getUserReviewList(String userEmail) {
		return reviewDao.getUserReviewList(userEmail);
	}

}

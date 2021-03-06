package com.pjt2.lb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt2.lb.entity.QBook;
import com.pjt2.lb.response.BookListInfoRes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class BookRepositorySupport {

	@Autowired
	private JPAQueryFactory query;
	
	QBook qBook = QBook.book;
	
	public List<BookListInfoRes> getSearchBookInfo(String searchKey, String searchWord){
		if(searchKey.equals("001")) {
			List<BookListInfoRes> bookList = query.select(Projections.bean(BookListInfoRes.class, qBook.bookIsbn, qBook.bookTitle, qBook.bookImgUrl))
					.from(qBook).where(qBook.bookTitle.contains(searchWord).or(qBook.bookAuthor.contains(searchWord)))
					.fetch();
			return bookList;
		} else if (searchKey.equals("002")) {
			List<BookListInfoRes> bookList = query.select(Projections.bean(BookListInfoRes.class, qBook.bookIsbn, qBook.bookTitle, qBook.bookImgUrl))
					.from(qBook).where(qBook.bookTitle.contains(searchWord))
					.fetch();
			return bookList;
		} else {
			List<BookListInfoRes> bookList = query.select(Projections.bean(BookListInfoRes.class, qBook.bookIsbn, qBook.bookTitle, qBook.bookImgUrl))
					.from(qBook).where(qBook.bookAuthor.contains(searchWord))
					.fetch();
			return bookList;
		}
	}
	
	public BookListInfoRes getListBookInfo(String bookIsbn) {
		BookListInfoRes bookListInfoRes = query.select(Projections.bean(BookListInfoRes.class, qBook.bookIsbn, qBook.bookTitle, qBook.bookImgUrl))
		.from(qBook).where(qBook.bookIsbn.eq(bookIsbn)).fetchOne();
		return bookListInfoRes;
	}

	public List<BookListInfoRes> getFirstBookList() {
		List<BookListInfoRes> firstBookList = query.select(Projections.bean(BookListInfoRes.class, qBook.bookIsbn, qBook.bookTitle, qBook.bookImgUrl))
				.from(qBook).orderBy(qBook.bookLikeCnt.desc()).limit(30)
				.fetch();
		return firstBookList;
	}
}

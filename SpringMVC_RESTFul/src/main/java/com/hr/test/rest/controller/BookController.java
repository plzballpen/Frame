package com.hr.test.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hr.test.common.domain.Book;
import com.hr.test.common.service.BookService;
import com.hr.test.rest.domain.BookList;
import com.hr.test.rest.exception.ResourceNotFoundException;

@Controller
@RequestMapping(value = "/books")
public class BookController {
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	@Autowired
	BookService bookService;

	 /*XML형태 = JAXB에서는 List<E>형태의 객체를 XML로 변환할 수 없으므로 도서 정보 목록을 가져오는 것을 수정한다.*/
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public BookList getBooks() {
		List<Book> books = bookService.getBooks();
		return new BookList(books);
	}
	
	/*JSON 형태
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Book> getBooks() {
		List<Book> books = bookService.getBooks();
		return books;
	}
	*/
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Book getBook(@PathVariable("id") Long id) {
		Book book = bookService.getBook(id);
		
		//Exception처리
		if(book == null){
			throw new ResourceNotFoundException();
		}
		
		return book;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Book createBook(@RequestBody Book book) {
		bookService.createBook(book);
		Book selectedBook = bookService.getBook(book.getId());
		return selectedBook;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
		bookService.updateBook(book);
		Book selectedBook = bookService.getBook(book.getId());
		return selectedBook;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Book deleteBook(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
		Book deletedBook = new Book();
		deletedBook.setId(id);
		return deletedBook;
	}
}

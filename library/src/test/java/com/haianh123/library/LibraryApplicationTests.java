package com.haianh123.library;

import com.haianh123.library.dto.response.BooksResponse;
import com.haianh123.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LibraryApplicationTests {

	@Autowired
	BookService bookService;
	@Test
	void contextLoads() {
		BooksResponse books = bookService.getAllBooks(0, 2, "name", "asc");
		System.out.println(books);
	}

}

package com.haianh123.library.service.impl;

import com.haianh123.library.dto.response.BookResponse;
import com.haianh123.library.dto.response.BooksResponse;
import com.haianh123.library.entity.Book;
import com.haianh123.library.mapper.BookMapper;
import com.haianh123.library.repository.BookRepository;
import com.haianh123.library.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;
    BookMapper bookMapper;

    @Override
    public BooksResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        //create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Book> books = bookRepository.findAll(pageable);

        // Get content for page object
        List<Book> listOfBooks = books.getContent();
        List<BookResponse> content = listOfBooks.stream().map(book -> bookMapper.toBookResponse(book)).collect(Collectors.toList());

        // Set BooksResponse
        BooksResponse booksResponse = new BooksResponse();
        booksResponse.setContent(content);
        booksResponse.setPageNo(books.getNumber());
        booksResponse.setPageSize(books.getSize());
        booksResponse.setTotalElements(books.getTotalElements());
        booksResponse.setTotalPages(books.getTotalPages());
        booksResponse.setLast(books.isLast());

        return booksResponse;
    }
}

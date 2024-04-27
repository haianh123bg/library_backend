package com.haianh123.library.service.impl;

import com.haianh123.library.dto.request.BookRequest;
import com.haianh123.library.dto.response.BookResponse;
import com.haianh123.library.dto.response.BooksResponse;
import com.haianh123.library.entity.Book;
import com.haianh123.library.exception.AppException;
import com.haianh123.library.exception.ErrorCode;
import com.haianh123.library.mapper.BookMapper;
import com.haianh123.library.repository.AuthorRepository;
import com.haianh123.library.repository.BookRepository;
import com.haianh123.library.repository.CategoryRepository;
import com.haianh123.library.repository.PublisherRepository;
import com.haianh123.library.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;
    CategoryRepository categoryRepository;
    AuthorRepository authorRepository;
    PublisherRepository publisherRepository;
    BookMapper bookMapper;

    @Override
    public BooksResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        //create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Book> books = bookRepository.findAll(pageable);
        log.info(books.toString());

        // Get content for page object
        List<Book> listOfBooks = books.getContent();
        listOfBooks.forEach(book ->{
            System.out.println(book);
        });
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

    @Override
    public BookResponse getBookById(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorCode.BOOK_NOT_EXISTED));
        return bookMapper.toBookResponse(book);
    }

    @Override
    public BookResponse createBook(BookRequest bookRequest) {
        Book book = bookMapper.toBook(bookRequest);
        book.setAuthor(authorRepository.findById(bookRequest.getAuthor()).orElseThrow(
                () -> new AppException(ErrorCode.AUTHOR_NOT_EXISTED)
        ));
        book.setCategory(categoryRepository.findById(bookRequest.getCategory()).orElseThrow(
                () -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED)
        ));
        book.setPublisher(publisherRepository.findById(bookRequest.getPublisher()).orElseThrow(
                () -> new AppException(ErrorCode.PUBLISHER_NOT_EXISTED)
        ));
        Book bookCreate = bookRepository.save(book);
        return bookMapper.toBookResponse(bookCreate);
    }

    @Override
    public void deleteBook(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_EXISTED));
        bookRepository.delete(book);

    }

    @Override
    public BookResponse updateBook(BookRequest bookRequest, int id) {
        if (!bookRepository.existsById(id)){
            throw new AppException(ErrorCode.BOOK_NOT_EXISTED);
        }
        Book book = bookMapper.toBook(bookRequest);

        book.setId(id);
        book.setAuthor(authorRepository.findById(bookRequest.getAuthor()).orElseThrow(
                () -> new AppException(ErrorCode.AUTHOR_NOT_EXISTED)
        ));
        book.setCategory(categoryRepository.findById(bookRequest.getCategory()).orElseThrow(
                () -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED)
        ));
        book.setPublisher(publisherRepository.findById(bookRequest.getPublisher()).orElseThrow(
                () -> new AppException(ErrorCode.PUBLISHER_NOT_EXISTED)
        ));
        Book bookCreate = bookRepository.save(book);
        return bookMapper.toBookResponse(bookCreate);
    }
}


















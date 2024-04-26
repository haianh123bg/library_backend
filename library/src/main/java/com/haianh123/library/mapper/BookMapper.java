package com.haianh123.library.mapper;

import com.haianh123.library.dto.response.BookResponse;
import com.haianh123.library.entity.Book;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponse toBookResponse(Book book);
}

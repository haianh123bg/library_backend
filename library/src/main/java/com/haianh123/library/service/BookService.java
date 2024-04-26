package com.haianh123.library.service;

import com.haianh123.library.dto.response.BooksResponse;

public interface BookService {

    BooksResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

}

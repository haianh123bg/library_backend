package com.haianh123.library.service;

import com.haianh123.library.dto.request.UserCreateRequest;
import com.haianh123.library.dto.response.UserResponse;

public interface UserService {
    UserResponse createUser(UserCreateRequest request);
}

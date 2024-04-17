package com.haianh123.library.controller;

import com.haianh123.library.dto.request.UserCreateRequest;
import com.haianh123.library.dto.response.ApiResponse;
import com.haianh123.library.dto.response.UserResponse;
import com.haianh123.library.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping(value = {"/sigup", "/login"})
    public ApiResponse<UserResponse> createUser(@RequestBody UserCreateRequest request){
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.createUser(request))
                .build();
    }
}

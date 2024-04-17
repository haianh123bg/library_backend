package com.haianh123.library.service.impl;

import com.haianh123.library.dto.request.UserCreateRequest;
import com.haianh123.library.dto.response.UserResponse;
import com.haianh123.library.entity.User;
import com.haianh123.library.exception.AppException;
import com.haianh123.library.exception.ErrorCode;
import com.haianh123.library.mapper.UserMapper;
import com.haianh123.library.repository.UserRepository;
import com.haianh123.library.service.UserService;
import com.haianh123.library.utils.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    @Override
    public UserResponse createUser(UserCreateRequest request) {
        // Kiểm tra xem userAccountName có tồn tại rồi hay không
        if(userRepository.existsByUserAccountName(request.getUserAccountName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        // Xét role
        user.setUserRole(Role.USER.name());

        return userMapper.toUserResponse(userRepository.save(user));
    }
}

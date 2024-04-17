package com.haianh123.library.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    private String userName;
    private String userImage;
    private String userAccountName;
    private String userAccountPassword;
    private String userPhoneNumber;
}

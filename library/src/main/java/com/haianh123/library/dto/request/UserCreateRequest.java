package com.haianh123.library.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    @NotEmpty
    private String userName;

    @Email(message = "INVALID_EMAIL")
    private String userAccountName;

    
    private String userAccountPassword;
}

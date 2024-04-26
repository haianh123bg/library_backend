package com.haianh123.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {

    private String name;

    private int totalBooks;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}


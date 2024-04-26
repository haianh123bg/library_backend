package com.haianh123.library.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCreateRequest {

        private String name;

        private int totalBooks;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

}

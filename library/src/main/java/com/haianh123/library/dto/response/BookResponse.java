package com.haianh123.library.dto.response;

import com.haianh123.library.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class BookResponse {

    private int id;
    private String name;
    private int publishingYear;
    private String description;
    private BigDecimal price;
    private int inventoryNumber;
    private int pageNumber;
    private String status;
    private String language;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private float weight;
    private String size;
    private Author author;
    private Category category;
    private Publisher publisher;
    private List<Image> images;
    private List<CouponCode> couponCodes;
}

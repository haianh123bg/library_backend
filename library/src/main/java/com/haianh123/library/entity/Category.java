package com.haianh123.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblcategory")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private int id;

    @Column(name = "category_name", nullable = false, length = 255)
    private String name;

    @Column(name = "category_total_book", nullable = false)
    private int totalBooks;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH
            })
    private List<Book> books;
}
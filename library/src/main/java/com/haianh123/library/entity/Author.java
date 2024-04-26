package com.haianh123.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblauthor")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", nullable = false)
    private int id;

    @Column(name = "author_name", nullable = false, length = 255)
    private String name;

    @Column(name = "author_date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "author_description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "author_image", length = 255)
    private String image;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "author",
            cascade = {
                    CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH
            })
    private List<Book> books;


}
package com.omkar.porfolio_backend.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)   // ✅ was missing — prevents category stored as int ordinal
    @Column(nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    private BlogStatus status = BlogStatus.DRAFT;

    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime publishedAt;
    private long viewCount = 0;
}

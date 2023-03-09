package kr.dohoonkim.blog.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

//@Entity
//@Table(name="article")
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Getter
//@Setter
//public class Article extends BaseEntity {
//  @Id
//  @GeneratedValue(strategy=GenerationType.IDENTITY)
//  private Long id;
//
//  @Column(name = "title", nullable = false, length=256)
//  private String title;
//
//  @Column(name = "content", nullable = false)
//  private String content;
//
//  @Column(name="created_at")
//  @CreatedDate
//  private LocalDateTime createdAt;
//
//  private Long categoryId;
//
//  private Long authorId;
//}

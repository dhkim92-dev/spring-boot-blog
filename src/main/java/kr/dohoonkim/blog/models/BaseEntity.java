package kr.dohoonkim.blog.models;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity {
  @CreatedDate
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", updatable = true)
  private LocalDateTime updatedAt;

  @PrePersist
  protected void basePrePersist(){
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void basePreUpdate(){
    this.updatedAt = LocalDateTime.now();
  }
}

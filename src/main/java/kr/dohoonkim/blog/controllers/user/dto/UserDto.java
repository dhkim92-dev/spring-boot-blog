package kr.dohoonkim.blog.controllers.user.dto;


import kr.dohoonkim.blog.models.User;
import lombok.*;

import java.time.LocalDateTime;
import static com.google.common.base.Preconditions.checkArgument;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
  private Long id;

  private String name;

  private String email;

  private boolean isAdmin;

  private boolean isActive;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public UserDto(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.createdAt = user.getCreatedAt();
    this.updatedAt = user.getUpdatedAt();
    this.isActive = user.getIsActive();
    this.isAdmin = user.getIsAdmin();
  }
}

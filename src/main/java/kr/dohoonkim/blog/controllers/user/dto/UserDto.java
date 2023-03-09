package kr.dohoonkim.blog.controllers.user.dto;


import kr.dohoonkim.blog.models.User;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import static com.google.common.base.Preconditions.checkArgument;

@ToString
@Getter
public class UserDto {
  private final Long id;

  private final String name;

  private final String email;

  private final boolean isAdmin;

  private final boolean isActive;

  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

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

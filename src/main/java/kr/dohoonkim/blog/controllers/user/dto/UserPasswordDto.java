package kr.dohoonkim.blog.controllers.user.dto;

import kr.dohoonkim.blog.utilities.EmailValidator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class UserPasswordDto {

  private String password;

  public UserPasswordDto(String password){
    this.password =password;
  }
}

package kr.dohoonkim.blog.controllers.user.dto;

import kr.dohoonkim.blog.utilities.EmailValidator;

import static com.google.common.base.Preconditions.checkArgument;

public class UserJoinDto {
  private final String name;

  private final String email;

  private final String password;

  public UserJoinDto(String name, String email, String password){
    checkArgument(name != null, "name must be provided.");
    checkArgument(name.length() > 0 && name.length() < 50, "invalid name length");
    checkArgument(email != null, "email must be provided.");
    checkArgument(EmailValidator.getInstance().validate(email), "invalid email format");
    checkArgument(password != null, "password must be provided.");

    this.name = name;
    this.email = email;
    this.password =password;
  }
}

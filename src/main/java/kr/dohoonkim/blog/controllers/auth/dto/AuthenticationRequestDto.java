package kr.dohoonkim.blog.controllers.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationRequestDto {
  String email;
  String password;

}

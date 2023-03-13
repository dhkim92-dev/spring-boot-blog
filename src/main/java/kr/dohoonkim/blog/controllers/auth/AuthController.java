package kr.dohoonkim.blog.controllers.auth;

import kr.dohoonkim.blog.common.responses.ApiResult;
import kr.dohoonkim.blog.controllers.auth.dto.AuthenticationRequestDto;
import kr.dohoonkim.blog.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static kr.dohoonkim.blog.common.responses.ApiResult.OK;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  private UserService userService;

  public AuthController(UserService userService){
    this.userService = userService;
  }

  @PostMapping
  public ApiResult<String> authenticate(@RequestBody AuthenticationRequestDto authenticationRequestDto){
    this.userService.findByEmail(authenticationRequestDto.getEmail());
    return OK(authenticationRequestDto.toString());
  }
}

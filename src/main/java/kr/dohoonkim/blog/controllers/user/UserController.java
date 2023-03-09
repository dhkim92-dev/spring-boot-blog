package kr.dohoonkim.blog.controllers.user;


import kr.dohoonkim.blog.common.dto.GenericListResponse;
import kr.dohoonkim.blog.common.errors.BadRequestException;
import kr.dohoonkim.blog.common.responses.ApiResult;
import kr.dohoonkim.blog.controllers.user.dto.UserDto;
import kr.dohoonkim.blog.controllers.user.dto.UserJoinDto;
import kr.dohoonkim.blog.models.User;
import kr.dohoonkim.blog.repositories.user.UserRepository;
import kr.dohoonkim.blog.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;
import static kr.dohoonkim.blog.common.responses.ApiResult.OK;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
public class UserController {
  private UserService userService;

  UserController(UserService userService){
    this.userService = userService;
  }


  @GetMapping
  public ApiResult<GenericListResponse<UserDto>> getAllUsers(){
    return OK(this.userService.findUsers());
  }
//
//  @GetMapping("/{userId}")
//  public ApiResult<UserDto> getUser(@RequestParam int userId){
//    return this.userService.findUserById(userId);
//  }

  @GetMapping("/email")
  public ApiResult<UserDto> getUserByEmail(@RequestParam("address") String address){
    UserDto userDto = this.userService.findByEmail(address);
    return OK(userDto);
  }

//  @PostMapping
//  public ApiResult<UserDto> joinUser(@RequestBody UserJoinDto userJoinDto){
//
//  }

//  @PutMapping("/{userId}")
//  public ApiResult<UserDto> updateUserInfo(){
//
//  }
//
//  @DeleteMapping("/{userId}")
//  public ApiResult<Boolean> deleteUser(@PathVariable Long userId){
//    return OK(true);
//  }
}

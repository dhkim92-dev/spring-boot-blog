package kr.dohoonkim.blog.controllers.user;


import kr.dohoonkim.blog.common.dto.GenericListResponse;
import kr.dohoonkim.blog.common.responses.ApiResult;
import kr.dohoonkim.blog.controllers.user.dto.UserDto;
import kr.dohoonkim.blog.controllers.user.dto.UserJoinDto;
import kr.dohoonkim.blog.controllers.user.dto.UserPasswordDto;
import kr.dohoonkim.blog.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static kr.dohoonkim.blog.common.responses.ApiResult.OK;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
public class UserController {
  private UserService userService;

  public UserController(UserService userService){
    this.userService = userService;
  }

  @GetMapping
  public ApiResult<GenericListResponse<UserDto>> getAllUsers(Pageable pageable){
    List<UserDto> users = this.userService.findUsers(pageable);
    return OK(new GenericListResponse<UserDto>(users.size(), users, pageable.getPageNumber(), pageable.getPageSize()));
  }

  @PostMapping
  public ApiResult<UserDto> joinUser(@RequestBody UserJoinDto userJoinDto){
    return OK(this.userService.createNewUser(userJoinDto));
  }

  @GetMapping("/{userId}")
  public ApiResult<UserDto> getUser(@PathVariable Long userId){
    return OK(this.userService.findUserById(userId));
  }

  @GetMapping("/email")
  public ApiResult<UserDto> getUserByEmail(@RequestParam("address") String address){
    return OK(this.userService.findByEmail(address));
  }

  @GetMapping("/email/exist")
  public ApiResult<Boolean> checkEmailExist(@RequestParam("address") String address){
    UserDto userDto = this.userService.findByEmail(address);
    return OK(userDto!=null);
  }

  @PutMapping("/{userId}")
  public ApiResult<UserDto> updateUserInfo(@PathVariable Long userId, @RequestBody UserPasswordDto userPasswordDto){
    return OK(this.userService.updateUserPassword(userId, userPasswordDto));
  }

  @DeleteMapping("/{userId}")
  public ApiResult<Boolean> deleteUser(@PathVariable Long userId){
    this.userService.deleteUser(userId);
    return OK(true);
  }
}

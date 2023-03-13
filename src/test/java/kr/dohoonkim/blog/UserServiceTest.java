package kr.dohoonkim.blog;

import jakarta.transaction.Transactional;
import kr.dohoonkim.blog.common.errors.BadRequestException;
import kr.dohoonkim.blog.controllers.user.dto.UserDto;
import kr.dohoonkim.blog.controllers.user.dto.UserJoinDto;
import kr.dohoonkim.blog.controllers.user.dto.UserPasswordDto;
import kr.dohoonkim.blog.models.User;
import kr.dohoonkim.blog.repositories.user.UserRepository;
import kr.dohoonkim.blog.services.UserService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@TestMethodOrder(value= MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class UserServiceTest {
  @Autowired
  private UserService userService;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Autowired
  private UserRepository userRepository;

  @DisplayName("유저 전체 조회 테스트")
  @Test
  @Order(1)
  public void userListTest(){
    Pageable pageable = PageRequest.of(0, 5);
    List< UserDto> users = this.userService.findUsers(pageable);

    assertThat(users.size()).isEqualTo(5);
    assertThat(users)
            .extracting("name", "email")
            .contains(tuple("test01", "test01@gmail.com"),
                    tuple("test02","test02@gmail.com"),
                    tuple("test03","test03@gmail.com"),
                    tuple("test04","test04@gmail.com"),
                    tuple("test05","test05@gmail.com"));

    pageable = PageRequest.of(1, 5);
    users = this.userService.findUsers(pageable);
    assertThat(users.size()).isEqualTo(5);
    assertThat(users)
            .extracting("name", "email")
            .contains(tuple("test06", "test06@gmail.com"),
                    tuple("test07","test07@gmail.com"),
                    tuple("test08","test08@gmail.com"),
                    tuple("test09","test09@gmail.com"),
                    tuple("test10","test10@gmail.com"));

    pageable = PageRequest.of(3, 5);
    users = this.userService.findUsers(pageable);
    assertThat(users).isEmpty();
  }

  @DisplayName("단일 유저 ID 조회")
  @Test
  @Order(2)
  public void getUserByIdTest(){
    UserDto user = this.userService.findUserById(1L);

    assertThat(user).isNotNull();
    assertThat(user.getEmail()).isEqualTo("test01@gmail.com");
    assertThat(user.getName()).isEqualTo("test01");
  }

  @DisplayName("단일 유저 Email 조회")
  @Test
  @Order(3)
  public void getUserByEmailTest(){
    UserDto user = this.userService.findByEmail("test01@gmail.com");
    assertThat(user).isNotNull();
    assertThat(user.getId()).isEqualTo(1);
    assertThat(user.getName()).isEqualTo("test01");
    assertThat(user.isAdmin()).isEqualTo(true);
  }

  @DisplayName("없는 유저 ID 조회")
  @Test
  @Order(4)
  public void getNotExistUserByIdTest(){
    assertThatThrownBy(()->{
      this.userService.findUserById(20L);
    }).isExactlyInstanceOf(NotFoundException.class);
  }

  @DisplayName("없는 유저 Email 조회")
  @Test
  @Order(5)
  public void getNotExistUserByEmailTest(){
    assertThatThrownBy(()->{
      this.userService.findByEmail("test21@gmail.com");
    }).isExactlyInstanceOf(NotFoundException.class);
  }

  @DisplayName("잘못된 Email 형식 테스트")
  @Test
  @Order(6)
  public void invalidEmailFormatTest(){
    assertThatThrownBy(()-> {
      this.userService.findByEmail("invalid email format");
    }).isExactlyInstanceOf(BadRequestException.class);
  }

  @DisplayName("유저 비밀번호 업데이트")
  @Test
  @Order(7)
  @Transactional
  public void modifyUserInfoTest(){
    User user = this.userRepository.findById(1L)
            .get();
    UserPasswordDto userPasswordDto = new UserPasswordDto("test");
    UserDto userDto = this.userService.updateUserPassword(1L, userPasswordDto);
    user = this.userRepository.findById(1L).get();
    assertThat(encoder.matches("test", user.getPassword()))
            .isEqualTo(true);
  }

  @DisplayName("유저 삭제 테스트")
  @Test
  @Order(8)
  @Transactional
  public void deleteUserTest(){
    User user = this.userRepository.findById(1L).get();

    assertThat(user.getName()).isEqualTo("test01");

    this.userService.deleteUser(user.getId());

    Optional<User> deletedUser = this.userRepository.findById(1L);
    assertThat(deletedUser.isPresent()).isEqualTo(false);
  }

  @DisplayName("없는 유저 삭제 테스트")
  @Test
  @Order(9)
  @Transactional
  public void deleteNotExistUserTest(){
    assertThatThrownBy(()->{
      this.userService.deleteUser(22L);
    }).isInstanceOf(NotFoundException.class)
      .hasMessage("Not exists user.");
  }
}

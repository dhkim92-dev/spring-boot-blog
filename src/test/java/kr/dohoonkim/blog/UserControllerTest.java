package kr.dohoonkim.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import kr.dohoonkim.blog.common.responses.GeneralExceptionHandler;
import kr.dohoonkim.blog.controllers.user.UserController;
import kr.dohoonkim.blog.controllers.user.dto.UserJoinDto;
import kr.dohoonkim.blog.controllers.user.dto.UserPasswordDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {
  @Autowired
  UserController userController;

  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @BeforeEach
  public void setup(){
    mockMvc = MockMvcBuilders.standaloneSetup(userController)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .setControllerAdvice(new GeneralExceptionHandler())
            .build();
  }

  @DisplayName("전체 유저 조회")
  @Test
  public void getUsersListTest() throws Exception {
  }

  @DisplayName("ID로 유저 조회")
  @Test
  public void getUserById() throws Exception {
    String endpoint = "/api/v1/users/{userId}";
    this.mockMvc.perform(get(endpoint, 1))
            .andExpect(status().isOk());

    this.mockMvc.perform(get(endpoint, 22))
            .andExpect(status().isNotFound());
  }

  @DisplayName("Email 주소로 유저 조회")
  @Test
  public void getUserByEmail() throws Exception {
    String endpoint = "/api/v1/users/email";
    this.mockMvc.perform(get(endpoint).queryParam("address","test01@gmail.com"))
            .andExpect(status().isOk());

    this.mockMvc.perform(get(endpoint).queryParam("address","noUser@gmail.com"))
            .andExpect(status().isNotFound());

    this.mockMvc.perform(get(endpoint).queryParam("address", "invalid email form"))
            .andExpect(status().isBadRequest());
  }

  @DisplayName("유저 회원 가입 - 정상 정보 입력")
  @Test
  public void createUserValidForm() throws Exception {
    UserJoinDto userPasswordDto = new UserJoinDto("normal","normal@gmail.com","1234");
    String endpoint = "/api/v1/users";

    String content = this.mapper.writeValueAsString(userPasswordDto);

    this.mockMvc.perform(post(endpoint)
            .content(content)
            .contentType("application/json")
    ).andExpect(status().isOk());
  }

  @DisplayName("유저 회원 가입 - 비정상 이메일 폼")
  @Test
  public void createUserInvalidEmailForm() throws Exception {
    UserJoinDto userPasswordDto = new UserJoinDto("normal","normal --- mail---com","1234");
    String endpoint = "/api/v1/users";

    String contents = this.mapper.writeValueAsString(userPasswordDto);

    this.mockMvc.perform(post(endpoint)
            .content(contents)
            .contentType("application/json")
    ).andExpect(status().isBadRequest());
  }

  @DisplayName("유저 회원 가입 - 비정상 이름 길이")
  @Test
  public void createUserInvalidNameLength() throws Exception {
    UserJoinDto userJoinDto = new UserJoinDto("normal121231231293817290371204075012739057901237849081230840912869071234324123412341234123412341234123412341234",
            "normal@gmail.com","1234");
    String endpoint = "/api/v1/users";

    String contents = this.mapper.writeValueAsString(userJoinDto);

    this.mockMvc.perform(post(endpoint)
            .content(contents)
            .contentType("application/json")
    ).andExpect(status().isBadRequest());
  }
  @DisplayName("회원 비밀번호 수정")
  public void updateUser() throws Exception {
    UserPasswordDto passwordDto = new UserPasswordDto("test");
    String endpoint = "/api/v1/users/{userId}";
    String content = mapper.writeValueAsString(passwordDto);
    this.mockMvc.perform(put(endpoint, 1L)
            .contentType("application/json")
            .content(content))
            .andExpect(status().isOk());
  }

  @DisplayName("회원 탈퇴")
  public void deleteUser() throws Exception {
    String endpoint = "/api/v1/users/{userId}";
    this.mockMvc.perform(delete(endpoint, 1L))
            .andExpect(status().isOk());
  }

}

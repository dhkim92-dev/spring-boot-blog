package kr.dohoonkim.blog.services;

import kr.dohoonkim.blog.common.dto.GenericListResponse;
import kr.dohoonkim.blog.common.errors.BadRequestException;
import kr.dohoonkim.blog.controllers.user.dto.UserDto;
import kr.dohoonkim.blog.repositories.user.UserRepository;
import kr.dohoonkim.blog.utilities.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  public GenericListResponse<UserDto> findUsers(){
    List<UserDto> users = this.userRepository.findAll()
            .stream()
            .map((u)->new UserDto(u))
            .collect(Collectors.toList());
    return new GenericListResponse<UserDto>(users.size(), users, 0, 5);
  }
  public UserDto findByEmail(String email){
    if(email == null){
      throw new BadRequestException("Email must be provided.");
    }

    if(!EmailValidator.getInstance().validate(email)){
      throw new BadRequestException("Invalid email format.");
    }

    return new UserDto(this.userRepository.findByEmail(email));
  }
}

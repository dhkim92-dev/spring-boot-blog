package kr.dohoonkim.blog.services;

import jakarta.transaction.Transactional;
import kr.dohoonkim.blog.common.errors.BadRequestException;
import kr.dohoonkim.blog.common.errors.ConflictException;
import kr.dohoonkim.blog.controllers.user.dto.UserDto;
import kr.dohoonkim.blog.controllers.user.dto.UserJoinDto;
import kr.dohoonkim.blog.controllers.user.dto.UserPasswordDto;
import kr.dohoonkim.blog.models.User;
import kr.dohoonkim.blog.repositories.user.UserRepository;
import kr.dohoonkim.blog.utilities.EmailValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.webjars.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.empty;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public List<UserDto> findUsers(Pageable pageable){
    PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

    List<UserDto> users = this.userRepository.findAll(pageRequest)
            .stream()
            .map((u)->new UserDto(u))
            .collect(Collectors.toList());

    return users;
  }
  public UserDto findByEmail(String email){
    System.out.println("email address : " + email);
    if(email == null){
      System.out.println("email must be provided.");
      throw new BadRequestException("Email must be provided.");
    }

    if(!EmailValidator.getInstance().validate(email)){
      System.out.println("email format invalid.");
      throw new BadRequestException("Invalid email format.");
    }

    return this.userRepository.findByEmail(email)
            .map(user -> new UserDto(user))
            .orElseThrow(()->{throw new NotFoundException("User not found.");});
  }

  public UserDto findByEmailAndPassword(String email, String password){
    return this.userRepository.findByEmail(email)
            .map((user)->{
              if(!passwordEncoder.matches(password, user.getPassword())){
              }
            })
            .orElseThrow(throw new NotFoundException("User not found."));
  }

  public UserDto findUserById(Long id){
    return this.userRepository.findById(id)
            .map(user->new UserDto(user))
            .orElseThrow(()->{throw new NotFoundException("User not found.");});
  }

  @Transactional
  public UserDto createNewUser(UserJoinDto userJoinDto){
    User user = User.builder()
            .name(userJoinDto.getName())
            .email(userJoinDto.getEmail())
            .password(
                    this.passwordEncoder.encode(userJoinDto.getPassword()))
            .build();

    User saved = this.userRepository.save(user);

    if(saved == null) throw new ConflictException("User create failed.");

    return new UserDto(saved);
  }

  @Transactional
  public UserDto updateUserPassword(Long userId, UserPasswordDto userPasswordDto){
    return new UserDto( this.userRepository.findById(userId)
            .map(user -> {
              user.setPassword(passwordEncoder.encode(userPasswordDto.getPassword()));
              return user;
            }).orElseThrow(()->{throw new NotFoundException("User not found.");}));
  }

  @Transactional
  public void deleteUser(Long userId){
   this.userRepository.findById(userId)
            .map(user->{
              this.userRepository.delete(user);
              return empty();})
            .orElseThrow(()->{throw new NotFoundException("Not exists user.");});
  }
}

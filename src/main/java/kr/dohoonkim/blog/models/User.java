package kr.dohoonkim.blog.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.dohoonkim.blog.utilities.EmailValidator;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import static com.google.common.base.Preconditions.checkArgument;

@Entity
@Table(name="bloguser")
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User extends BaseEntity{
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, length=50)
  private String name;

  @Column(name = "email", unique = true, nullable = false, length=60)
  private String email;

  @Column(name = "password", nullable = false, length=256)
  private String password;

  @Column(name = "is_admin")
  @ColumnDefault("false")
  private Boolean isAdmin;

  @Column(name = "is_active")
  @ColumnDefault("false")
  private Boolean isActive;

  public User(Long id, String name, String email, String password, Boolean isAdmin, Boolean isActive){
    checkArgument(name != null, "name must be provided.");
    checkArgument(name.length() > 0 && name.length() <= 50, "Name length must be in range(0,50)");
    checkArgument(email != null, "email must be provided.");
    checkArgument(email.length() > 0 && email.length() <= 60, "Email length must be in range(0, 60)");
    checkArgument(EmailValidator.getInstance().validate(email), "Invalid email format.");
    checkArgument(password!= null, "password must be provided.");
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.isAdmin = isAdmin;
    this.isActive = isActive;
  }

  @PrePersist
  private void prePersist(){
    this.isActive = false;
    this.isAdmin = false;
  }
}

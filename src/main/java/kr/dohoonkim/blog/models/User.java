package kr.dohoonkim.blog.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="bloguser")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User extends BaseEntity{
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "is_admin", nullable = false)
  private Boolean isAdmin;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;
}

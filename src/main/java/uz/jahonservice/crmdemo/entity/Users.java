package uz.jahonservice.crmdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.jahonservice.crmdemo.entity.enums.RoleEnum;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(nullable = false)
    private String phoneNumber;

    private String phoneNumberSecond;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private Admin admin;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private Teacher teacher;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private Student student;


    @OneToOne(mappedBy = "users",
            cascade = CascadeType.ALL
    )
    private RefreshToken refreshToken;
}

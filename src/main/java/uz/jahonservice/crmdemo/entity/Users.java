package uz.jahonservice.crmdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.jahonservice.crmdemo.entity.enums.RoleEnum;


import java.util.List;
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

    private RoleEnum role;

    @Column(nullable = false)
    private String phoneNumber;

    private String phoneNumberSecond;

    @OneToMany(
            mappedBy = "users",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    List<Groups> groups;

     @OneToMany(
            mappedBy = "users",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    List<Payments> paymentsList;

    @OneToMany(
            mappedBy = "users",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    List<Attendance> attendanceList;


}

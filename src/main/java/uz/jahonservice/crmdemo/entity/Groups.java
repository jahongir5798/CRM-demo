package uz.jahonservice.crmdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String groupName;

    private Long cost;

    @OneToMany(
            mappedBy = "groups",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Lesson> lessons;

    @OneToMany(
            mappedBy = "groups",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Attendance> attendanceList;

    @OneToMany(
            mappedBy = "groups",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Payments> paymentsList;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}

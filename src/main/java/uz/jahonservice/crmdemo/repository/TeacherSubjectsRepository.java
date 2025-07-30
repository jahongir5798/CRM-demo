package uz.jahonservice.crmdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jahonservice.crmdemo.entity.TeacherSubjects;

import java.util.UUID;

@Repository
public interface TeacherSubjectsRepository extends JpaRepository<TeacherSubjects, UUID> {


}

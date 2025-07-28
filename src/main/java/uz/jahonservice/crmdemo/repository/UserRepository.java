package uz.jahonservice.crmdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.jahonservice.crmdemo.entity.Users;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {

    Optional<Users> findByUserName(String userName);

    @Query("SELECT r.users FROM RefreshToken r WHERE r.token = :token")
    Optional<Users> findUserByToken(@Param("token") String token);

}

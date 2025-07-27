package uz.jahonservice.crmdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.jahonservice.crmdemo.entity.RefreshToken;
import uz.jahonservice.crmdemo.entity.Users;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUsers(Users user);  // mavjud tokenni olish

    void deleteByUsers(Users user);

    @Query("SELECT r FROM RefreshToken r JOIN r.users u WHERE u.userName = :username")
    Optional<RefreshToken> findByUsername(@Param("username") String username);
}

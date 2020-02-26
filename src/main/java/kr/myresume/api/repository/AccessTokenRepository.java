package kr.myresume.api.repository;

import kr.myresume.api.entity.session.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

    AccessToken findTopByTokenAndExpiredDtGreaterThanEqualOrderByExpiredDtDesc(String token, LocalDateTime now);
}

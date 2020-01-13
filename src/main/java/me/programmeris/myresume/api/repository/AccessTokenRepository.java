package me.programmeris.myresume.api.repository;

import me.programmeris.myresume.api.entity.session.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

    AccessToken findTopByKeyAndExpiredDTGreaterThanEqualOrderByExpiredDTDesc(String key, LocalDateTime now);
}

package me.programmeris.myresume.api.repository;

import me.programmeris.myresume.api.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);

    Page<User> findAllByEmail(String email, Pageable pageable);
}

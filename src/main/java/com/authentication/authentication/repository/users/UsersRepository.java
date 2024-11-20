package com.authentication.authentication.repository.users;

import com.authentication.authentication.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    UserDetails findByLogin(String login);
}

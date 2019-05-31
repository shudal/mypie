package moe.perci.pie.model.service;

import moe.perci.pie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface UserService extends JpaRepository<User, Long> {
    User findUserByUsernameAndStatus(String username, int status);
}

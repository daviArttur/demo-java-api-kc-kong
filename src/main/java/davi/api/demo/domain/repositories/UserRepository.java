package davi.api.demo.domain.repositories;

import davi.api.demo.domain.entities.Campaign;
import davi.api.demo.domain.entities.User;

import java.util.List;

public interface UserRepository {
    User findUserById(String id);
    List<User> findUsers();
    void save(User user);
}


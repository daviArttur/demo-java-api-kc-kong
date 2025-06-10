package davi.api.demo.infra.repositories;
//import org.springframework.data.jpa.repository.JpaRepository;


import davi.api.demo.domain.entities.User;
import davi.api.demo.domain.repositories.UserRepository;
import davi.api.demo.infra.repositories.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


interface UserJpaRepository extends JpaRepository<UserModel, Long> {}

class UserMapper {

    public static UserModel toEntity(User user) {
        UserModel entity = new UserModel();
        entity.id = user.id;
        entity.name = user.name;
        return entity;
    }

    public static User toDomain(UserModel entity) {
        return new User(entity.id, entity.name);
    }
}

@Repository
public class UserRepositoryHibernate implements UserRepository {

    private UserJpaRepository userJpaRepository;

    UserRepositoryHibernate(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User findUserById(String id) {
        return null;
    }

    @Override
    public List<User> findUsers() {
        return userJpaRepository.findAll().stream().map(UserMapper::toDomain).toList();
    }

    public void save(User user) {

    }

    //public Object findUserById(String userId);
}

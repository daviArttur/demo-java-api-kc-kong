package davi.api.demo.application.useCases;

import davi.api.demo.application.inputs.GetUserUseCaseInput;
import davi.api.demo.domain.entities.User;
import davi.api.demo.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUsersUseCase {
    private final UserRepository userRepository;

    public GetUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> perform() {
        try {
            return userRepository.findUsers();
        } catch (Exception e) {
            e.toString();
            return null;
        }
        //return userRepository.findUserById(input.userId);
    }
}

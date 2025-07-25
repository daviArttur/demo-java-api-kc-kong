package davi.api.demo.application.useCases;

import davi.api.demo.application.inputs.CreateUserInput;
import davi.api.demo.application.inputs.GetUserUseCaseInput;
import davi.api.demo.domain.entities.User;
import davi.api.demo.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void perform(CreateUserInput input) {
        try {
            var user = new User(null, input.email(), input.email());
            userRepository.save(user);
        } catch (Exception e) {
            e.toString();
        }
        //return userRepository.findUserById(input.userId);
    }
}

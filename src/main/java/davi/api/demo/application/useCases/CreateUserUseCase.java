package davi.api.demo.application.useCases;

import davi.api.demo.application.inputs.CreateUserInput;
import davi.api.demo.application.inputs.GetUserUseCaseInput;
import davi.api.demo.application.interfaces.PasswordService;
import davi.api.demo.domain.entities.User;
import davi.api.demo.domain.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordService passwordService;

    public CreateUserUseCase(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public void perform(CreateUserInput input) {
        try {
            var encodedPassword = passwordService.encode(input.password());
            var user = new User(null, input.email(), encodedPassword);
            userRepository.save(user);

        } catch (Exception e) {
            e.toString();
        }
        //return userRepository.findUserById(input.userId);
    }
}

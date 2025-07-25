package davi.api.demo.application.useCases;

import davi.api.demo.application.inputs.GetUserUseCaseInput;
import davi.api.demo.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

interface getUserUseCaseOutput {}

@Service
public class GetUserUseCase {

    private final UserRepository userRepository;

    public GetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Object perform(GetUserUseCaseInput input) {
        try {
            return userRepository.findUsers();
        } catch (Exception e) {
            e.toString();
            return null;
        }
        //return userRepository.findUserById(input.userId);
    }
}

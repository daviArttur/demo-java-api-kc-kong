package davi.api.demo.application.useCases;

import davi.api.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

interface getUserUseCaseOutput {}

class GetUserUseCaseInput {
    public String userId = "";

    public GetUserUseCaseInput(String userId) { this.userId = userId; };
}

@Service
public class GetUserUseCase {

    private final UserRepository userRepository;

    @Autowired
    public GetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Object perform(GetUserUseCaseInput input) {
        return userRepository.findUserById(input.userId);
    }
}

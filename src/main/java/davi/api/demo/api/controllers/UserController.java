package davi.api.demo.api.controllers;

import davi.api.demo.application.inputs.CreateUserInput;
import davi.api.demo.application.useCases.CreateUserUseCase;
import davi.api.demo.application.useCases.GetUserUseCase;
import davi.api.demo.application.inputs.GetUserUseCaseInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {

    private final GetUserUseCase getUserUseCase;
    private final CreateUserUseCase createUserUseCase;

    @Autowired
    public UserController(GetUserUseCase getUserUseCase, CreateUserUseCase createUserUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.createUserUseCase = createUserUseCase;
    }

    @GetMapping("/users")
    public Object getUser(@RequestParam("userId") String userId) {
        return getUserUseCase.perform(new GetUserUseCaseInput(userId));
    }

    @PostMapping("/users")
    public void createUser(@RequestParam("email") String email) {
        createUserUseCase.perform(new CreateUserInput(email));
    }
}

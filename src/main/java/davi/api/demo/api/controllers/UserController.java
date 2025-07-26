package davi.api.demo.api.controllers;

import davi.api.demo.application.inputs.CreateUserInput;
import davi.api.demo.application.useCases.CreateUserUseCase;
import davi.api.demo.application.useCases.GetUserUseCase;
import davi.api.demo.application.inputs.GetUserUseCaseInput;
import davi.api.demo.application.useCases.GetUsersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {

    private final GetUserUseCase getUserUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final CreateUserUseCase createUserUseCase;

    @Autowired
    public UserController(GetUserUseCase getUserUseCase, CreateUserUseCase createUserUseCase, GetUsersUseCase getUsersUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.getUsersUseCase = getUsersUseCase;
        this.createUserUseCase = createUserUseCase;
    }

    @GetMapping("/users")
    public Object getUsers() {
        return getUsersUseCase.perform();
    }

    @PostMapping("/users")
    public void createUser(@RequestParam("email") String email) {
        createUserUseCase.perform(new CreateUserInput(email, email));
    }
}

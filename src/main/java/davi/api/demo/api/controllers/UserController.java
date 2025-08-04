package davi.api.demo.api.controllers;

import davi.api.demo.application.inputs.CreateUserInput;
import davi.api.demo.application.useCases.CreateUserUseCase;
import davi.api.demo.application.useCases.GetUserUseCase;
import davi.api.demo.application.inputs.GetUserUseCaseInput;
import davi.api.demo.application.useCases.GetUsersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController extends BaseController {
    private final GetUserUseCase getUserUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final CreateUserUseCase createUserUseCase;

    @GetMapping("/users")
    public Object getUsers() {
        return getUsersUseCase.perform();
    }

    @PostMapping("/users")
    public void createUser(@RequestParam("email") String email) {
        createUserUseCase.perform(new CreateUserInput(email, email));
    }
}

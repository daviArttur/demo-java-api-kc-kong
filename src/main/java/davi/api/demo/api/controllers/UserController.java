package davi.api.demo.api.controllers;

import davi.api.demo.application.useCases.GetUserUseCase;
import davi.api.demo.application.inputs.GetUserUseCaseInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {

    private final GetUserUseCase getUserUseCase;

    @Autowired
    public UserController(GetUserUseCase getUserUseCase) {
        this.getUserUseCase = getUserUseCase;
    }


    @GetMapping("/users")
    public Object getUser(@RequestParam("userId") String userId) {
        return getUserUseCase.perform(new GetUserUseCaseInput(userId));
    }
}

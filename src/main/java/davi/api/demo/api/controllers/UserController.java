package davi.api.demo.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController extends BaseController {

    @GetMapping("/users")
    public Map getUser() {
        return new HashMap();
    }
}

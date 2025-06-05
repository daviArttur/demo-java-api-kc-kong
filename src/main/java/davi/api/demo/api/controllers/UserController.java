package davi.api.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController extends BaseController {

    @GetMapping("/users")
    public Map getUser() {
        
    }
}

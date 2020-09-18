package hse.project.controllers.usercontrollers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user/customer")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class UserCustomerController {
}

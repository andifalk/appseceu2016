package info.novatec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class AuthorizationsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationsDemoApplication.class, args);
	}

	@RequestMapping(path = "/")
	public String index() {
		return "index";
	}

}

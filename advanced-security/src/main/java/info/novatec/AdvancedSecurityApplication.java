package info.novatec;

import info.novatec.user.User;
import info.novatec.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@SpringBootApplication
public class AdvancedSecurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AdvancedSecurityApplication.class, args);
	}

    @RequestMapping(path = "/")
    public String hello() {
        return "Hello AppSecEU 2016";
    }

	@Override
	public void run(String... strings) throws Exception {
		Stream.of(
			new User("user", passwordEncoder.encode("user_password")),
			new User("admin", passwordEncoder.encode("admin_password"))
		).forEach(u -> userRepository.save(u));
	}
}

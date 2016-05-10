package info.novatec.security.header;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import info.novatec.security.header.entity.MessageEntry;
import info.novatec.security.header.repository.MessageEntryRepository;

@SpringBootApplication
public class SecurityHeaderApplication implements CommandLineRunner {

    @Autowired
	private MessageEntryRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(SecurityHeaderApplication.class, args);
	}

	@Override
	public void run ( String... strings ) throws Exception {
        Stream.of (
                new MessageEntry ( "cookie", "<script>alert(document.cookie);</script>" ),
                new MessageEntry ( "hello", "<script>alert('hello xss');</script>" ) )
                .forEach ( me -> repo.save ( me ) );
	}
}

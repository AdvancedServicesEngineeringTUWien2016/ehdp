package at.ac.tuwien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class TwitterServiceApplication {
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello Twitter!";
	}

	public static void main(String[] args) {
		SpringApplication.run(TwitterServiceApplication.class, args);
	}
}

package demo;

import cloud.localstack.docker.LocalstackDockerExtension;
import cloud.localstack.docker.annotation.LocalstackDockerProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({LocalstackDockerExtension.class, SpringExtension.class})
@LocalstackDockerProperties(randomizePorts = true, services = { "sqs" })
 class AppTests {

	@Test
	 void contextLoads() {
	}

}

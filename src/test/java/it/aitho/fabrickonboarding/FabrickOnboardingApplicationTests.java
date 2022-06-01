package it.aitho.fabrickonboarding;

import it.aitho.fabrickonboarding.controller.FabrickAccountsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FabrickOnboardingApplicationTests {

	@Autowired
	private FabrickAccountsController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}

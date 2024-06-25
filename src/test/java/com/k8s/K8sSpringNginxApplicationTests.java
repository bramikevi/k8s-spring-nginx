package com.k8s;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "HOSTNAME=test-pod")
class K8sSpringNginxApplicationTests {

	@Test
	void contextLoads() {
	}

}

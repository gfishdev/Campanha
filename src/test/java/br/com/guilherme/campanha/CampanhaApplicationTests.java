package br.com.guilherme.campanha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CampanhaApplication.class)
@TestPropertySource(locations = "classpath:application.yml")
public class CampanhaApplicationTests {

	@Test
	public void contextLoads() {
	}

}

package com.cosmic.coroulette;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.cosmic.coroulette.dao.RandomNameGenerator;

@SpringBootTest
class CorouletteApplicationTests {

	@Test
	void contextLoads() {
		assertEquals("", RandomNameGenerator.RandomName());
	}

}

package com.dazt.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MsProductsApplicationTests {

	@Test
	void contextLoads() {
		MsProductsApplication.main(new String[] {});
		Assertions.assertTrue(true);
	}

}

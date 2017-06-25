package com.james;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import com.james.MongoApplication;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MongoApplication.class)
public class ApplicationTest {

	@ClassRule
	public static OutputCapture outputCapture = new OutputCapture();

	@Test
	public void testDefaultSettings() throws Exception {
		String output = ApplicationTest.outputCapture.toString();
		assertThat(output).contains("code: YYZ, name: Pearson");
		assertThat(output).contains("code: JFK, name: John F.Kennedy");
		
		assertThat(output).contains("planeName: Boing 747, company: Air Canada");
		assertThat(output).contains("planeName: Boing 777, company: Korean Air");
		
		assertThat(output).contains("booker: jamessung@g.com");
	}

}

package io.jdg;

import io.jdg.totp.EnableOneTimePasswordGeneration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableOneTimePasswordGeneration
public class SampleApp {
	public static void main(String[] a) {
		new SpringApplicationBuilder(SampleApp.class).web(true).run(a);
	}
}

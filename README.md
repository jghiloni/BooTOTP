# BooTOTP
A Spring Boot Library for Time-based One Time Password (TOTP) Generation

## Basic Usage
```java
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
```

Application Properties:
```yaml
security:
  totp:
    providers:
      provider1:
        name: Provider 1
        key: SOMEBASE32ENCODEDKEY
      provider2:
        name: Provider 2
        key: SOMEOTHERBASE32ENCODEDKEY
      provider3:
        name: Provider 3
        key: YETANOTHERBASE32KEY
```

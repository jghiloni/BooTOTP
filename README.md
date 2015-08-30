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

Start the app using `mvn spring-boot:run` and visit `http://localhost:8080`

This was designed as an `@Enable`d library to allow users to secure their UI in whatever way they chose--and it is
*HIGHLY* recommended that you secure this UI as much as you can. You may also disable the UI altogether by adding the
property `security.totp.dashboardEnabled=false`, and simply take advantage of the `@Autowire`able bean of type
`io.jdg.totp.service.OneTimePasswordGenerator`.

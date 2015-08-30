package io.jdg.totp.config;

import io.jdg.totp.OneTimePasswordProperties;
import io.jdg.totp.service.OneTimePasswordGenerator;
import io.jdg.totp.web.TotpController;
import io.jdg.totp.web.TotpDashboard;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OneTimePasswordProperties.class)
public class EnableTotpConfig {
	@Bean
	@ConditionalOnMissingBean(OneTimePasswordGenerator.class)
	public OneTimePasswordGenerator generator() {
		return new OneTimePasswordGenerator();
	}

	@Bean
	@ConditionalOnMissingBean(TotpController.class)
	public TotpController controller() {
		return new TotpController();
	}

	@Bean
	@ConditionalOnMissingBean(TotpDashboard.class)
	@ConditionalOnProperty(prefix = "security.totp", name = "dashboardEnabled", matchIfMissing = true)
	public TotpDashboard dashboard() {
		return new TotpDashboard();
	}
}

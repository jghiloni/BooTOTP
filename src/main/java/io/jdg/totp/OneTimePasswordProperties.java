package io.jdg.totp;

import java.util.Map;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "security.totp")
public class OneTimePasswordProperties {
	private boolean dashboardEnabled = true;
	
	private Map<String, ProviderDetails> providers;
	
	@Data
	public static class ProviderDetails {
		private String name;
		
		private String key;
	}
}

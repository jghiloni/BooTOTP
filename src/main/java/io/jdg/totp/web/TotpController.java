package io.jdg.totp.web;

import io.jdg.totp.OneTimePasswordProperties;
import io.jdg.totp.OneTimePasswordProperties.ProviderDetails;
import io.jdg.totp.service.OneTimePasswordGenerator;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
public class TotpController {
	@Autowired
	private OneTimePasswordProperties properties;

	@Autowired
	private OneTimePasswordGenerator generator;

	@RequestMapping(value = "/keys", produces = MediaType.APPLICATION_JSON_VALUE)
	public KeyResponse getKeys() throws NoSuchAlgorithmException, InvalidKeyException {
		Map<String, ProviderDetails> providers = properties.getProviders();

		KeyResponse response = new KeyResponse();

		Map<String, KeyResponse.Provider> keys = new LinkedHashMap<String, KeyResponse.Provider>();
		if (!CollectionUtils.isEmpty(providers)) {
			for (Map.Entry<String, ProviderDetails> entry : providers.entrySet()) {
				String providerName = entry.getKey();
				ProviderDetails details = entry.getValue();
				String key = generator.generatePassword(details.getKey());
				
				KeyResponse.Provider provider = new KeyResponse.Provider();
				provider.setDisplayName(details.getName());
				provider.setKey(key);
				
				keys.put(providerName, provider);
			}
		}
		
		response.setProviders(keys);
		return response;
	}

	@Data
	private static class KeyResponse {
		private Map<String, Provider> providers;

		@Data
		private static class Provider {
			@JsonProperty("display_name")
			private String displayName;

			private String key;
		}
	}
}

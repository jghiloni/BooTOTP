package io.jdg.totp.web;

import io.jdg.totp.OneTimePasswordProperties;
import io.jdg.totp.OneTimePasswordProperties.ProviderDetails;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TotpDashboard {
	@Autowired
	private OneTimePasswordProperties props;

	@RequestMapping("/")
	public String renderDashboard(Model model) {
		model.addAttribute("providerJson", getProviderMap());
		return "index";
	}

	private Map<String, String> getProviderMap() {
		Map<String, String> providers = new LinkedHashMap<String, String>();
		if (!CollectionUtils.isEmpty(props.getProviders())) {
			for (Map.Entry<String, ProviderDetails> entry : props.getProviders().entrySet()) {
				providers.put(entry.getKey(), entry.getValue().getName());
			}
		}
		
		return providers;
	}
}

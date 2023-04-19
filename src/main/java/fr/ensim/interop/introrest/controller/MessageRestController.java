package fr.ensim.interop.introrest.controller;

import ch.qos.logback.core.net.server.Client;
import fr.ensim.interop.introrest.model.telegram.Message;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessageRestController {
	

	@Value("$telegram.bot.id")
	private String ChatId;

	@Value("$bot.token")
	private String botToken;

	@PostMapping("/message")
	public String sendMessage(@RequestParam("chatId") String chatId,
							  @RequestParam("text") String text) {
		String apiUrl = "https://api.telegram.org/bot" + "6002365819:AAEDNFLH1_M-khwWCHH8ZoOXkXs-AuQ2Wu0"+ "/sendMessage";
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("chat_id", String.valueOf(2020129742));
		requestParams.add("text", text);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestParams, new HttpHeaders());
		ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
		return responseEntity.getBody();
	}

}






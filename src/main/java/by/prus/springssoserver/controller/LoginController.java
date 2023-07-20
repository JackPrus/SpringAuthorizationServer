package by.prus.springssoserver.controller;

import com.sun.net.httpserver.Headers;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/login-by-ip")
	public String loginByIp(HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {

		String userIp = request.getRemoteAddr();
		return "{"+userIp+"}";

//		String userIp = request.getRemoteAddr();
//
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
//		map.add("username", "login-by-ip:"+userIp);
//		map.add("password","");
//
//		String loginUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/login";
//
//		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);
//		ResponseEntity<String> responseEntity = restTemplate.postForEntity(loginUrl, requestEntity, String.class);
//		HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
//
//		// если авторизация успешна, перенаправляем на домашнюю страницу
//		if (!statusCode.is2xxSuccessful()) {
//			return "login";
//		}
//		return null;
	}

	@GetMapping("/get-ip")
	@ResponseBody
	public Map<String, String> getIp(HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
		String userIp = request.getRemoteAddr();
		Map<String, String> responseData = new HashMap<>();
		responseData.put("ip", userIp);
		return responseData;
	}
}

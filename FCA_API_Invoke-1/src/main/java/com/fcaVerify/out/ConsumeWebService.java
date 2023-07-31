package com.fcaVerify.out;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@PropertySource("classpath:application.properties")
@RestController
public class ConsumeWebService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private Environment env;	
	
	@RequestMapping(value = "/hello")
	public String hello() {
		return "Hello World1";
	}
	
	@RequestMapping(value = "/getAddress")
	private String getClientAddress() 
	{
		String uri = "https://register.fca.org.uk/services/V0.1/Firm/615821/Address";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = prepareHeader();
	    HttpEntity<String> entity = new HttpEntity<String>(headers);  
	    return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
	}
	
	@RequestMapping(value = "/getDetails")
	private String getClientDetails() 
	{
		String uri = "https://register.fca.org.uk/services/V0.1/Firm/615821/";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = prepareHeader();
	    HttpEntity<String> entity = new HttpEntity<String>(headers);  
	    return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping(value = "/getOtherNames")
	private String getClientNames() 
	{
		String uri = "https://register.fca.org.uk/services/V0.1/Firm/615821/Names";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = prepareHeader();
	    HttpEntity<String> entity = new HttpEntity<String>(headers);  
	    return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
	}
	
	@RequestMapping(value = "/getPermission")
	private String getClientPermissions() 
	{
		String uri = "https://register.fca.org.uk/services/V0.1/Firm/615821/Permissions";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = prepareHeader();
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    
	    return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
	}

	
	private HttpHeaders prepareHeader() {
		
		String id = env.getProperty("user");
		String key = env.getProperty("key");
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Auth-Email", id);
		headers.add("X-Auth-Key", key);
		headers.add("Content-Type", "application/json");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}
}

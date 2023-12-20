package com.hcl.upSkillConsumeProject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.upSkillConsumeProject.external.services.UpskillService;
import com.hcl.upSkillConsumeProject.model.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ServiceLayer {


    private final RestTemplate restTemplate;

    @Autowired
    private UpskillService upskillService;

    @Autowired
    public ServiceLayer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Cacheable(cacheNames = "products")
    @HystrixCommand(groupKey = "sahil", commandKey = "sahil", fallbackMethod = "getProductFallBack")
    public List<Product> getProducts() {
        System.out.println("calling API");
//        log.info("Calling API");
//        return restTemplate.exchange("http://UPSKILL/products", HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {}).getBody();
        return upskillService.getProducts();
    }

    public List<Product> getProductFallBack() {
        List<Product> list = new ArrayList<Product>();
        return list;
    }

    public Product saveProduct(Product product) {
        String requestBody = new ObjectMapper().valueToTree(product).toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody,headers);
        return restTemplate.exchange("http://UPSKILL/addProduct", HttpMethod.POST, entity,Product.class).getBody();

    }

    public Product updateProduct(Product product) {
        String requestBody = new ObjectMapper().valueToTree(product).toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody,headers);
        return restTemplate.exchange("http://UPSKILL/updateProduct", HttpMethod.PUT, entity, Product.class).getBody();
    }

    public String deleteProduct(int id) {
        return restTemplate.exchange("http://UPSKILL/deleteProduct/"+id, HttpMethod.DELETE, null, String.class).getBody();
    }
}

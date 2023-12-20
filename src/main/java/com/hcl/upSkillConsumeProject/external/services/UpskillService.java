package com.hcl.upSkillConsumeProject.external.services;

import com.hcl.upSkillConsumeProject.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "UPSKILL")
public interface UpskillService {
    @GetMapping("/products")
    List<Product> getProducts();
}

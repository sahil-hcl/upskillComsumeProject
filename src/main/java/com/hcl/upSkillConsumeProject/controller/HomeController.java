package com.hcl.upSkillConsumeProject.controller;

import com.hcl.upSkillConsumeProject.model.Product;
import com.hcl.upSkillConsumeProject.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class HomeController {

    private final ServiceLayer serviceLayer;

    @Autowired
    public HomeController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }
    @GetMapping("/products")
    public List<Product> getProduct(){
        return serviceLayer.getProducts();
    }
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        return serviceLayer.saveProduct(product);
    }
    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product){
        return serviceLayer.updateProduct(product);
    }
    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id){
        return serviceLayer.deleteProduct(id);
    }
}

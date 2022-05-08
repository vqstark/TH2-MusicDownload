package com.example.th2musicdownload.controller;

import com.example.th2musicdownload.model.Product;
import com.example.th2musicdownload.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String viewMain(){
        return "index";
    }

    @GetMapping("/displayProducts")
    public String getAll(Model model){
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "viewProducts";
    }

    @GetMapping("/delete/{productCode}")
    public String delete(@PathVariable String productCode, Model model){
        Product product = productRepository.findByProductCode(productCode);
        model.addAttribute("product", product);
        return "delete";
    }

    @PostMapping("/delete/{productCode}")
    public String deleteProduct(@PathVariable String productCode){
        System.out.println("productCode: " + productCode);
        Product product = productRepository.findByProductCode(productCode);
        productRepository.delete(product);
        return "redirect:/product/displayProducts";
    }
}

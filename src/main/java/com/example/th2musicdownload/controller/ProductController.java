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
}

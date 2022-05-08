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

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/create")
    public String createProduct(@RequestParam String productCode,
                                @RequestParam String description,
                                @RequestParam String price,
                                Model model){
        String message = "";
        Double price1 = 0d;
        try{
            price1 = Double.parseDouble(price);
            if(price1 < 0){
                message = "You must enter product code for product";
            }
        }catch(NumberFormatException e){
            price1 = 0d;
        }
        Product product = new Product(productCode, description, price1);
        model.addAttribute("product", product);
        if(productCode==null || productCode.length()==0){
            message = "You must enter product code for product";
            model.addAttribute("message", message);
            return "create";
        }
        if(description==null || description.length()==0){
            message = "You must enter description for product";
            model.addAttribute("message", message);
            return "create";
        }

        productRepository.save(new Product(productCode, description, Double.parseDouble(price)));
        return "redirect:/product/displayProducts";
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

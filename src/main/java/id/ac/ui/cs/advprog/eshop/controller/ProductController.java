package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    private final ProductService service;
    
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String createProductPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model){
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable("productId") String productId, Model model){
        Product product = service.findProductById(productId);
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute Product product, Model model) {
        service.edit(product);
        return "redirect:list";
    }

    @PostMapping("/delete/{productId}")
    public String deleteProductPost(@PathVariable("productId") String productId, Model model){
        service.delete(productId);
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public String productListPage(Model model){
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }
    
}

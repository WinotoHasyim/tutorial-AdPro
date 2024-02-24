package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(view().name("CreateProduct"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        Product product = productInitializer("1", "New Product", 20);
        mockMvc.perform(post("/product/create")
                .param("productId", product.getProductId())
                .param("productName", product.getProductName())
                .param("productQuantity", String.valueOf(product.getProductQuantity())))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void testEditProductPage() throws Exception {
        when(service.findById("1")).thenReturn(productInitializer("1", "Existing Product", 10));
        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(view().name("EditProduct"));
    }

    @Test
    void testEditProductPost() throws Exception {
        mockMvc.perform(post("/product/edit")
                .param("productId", "1")
                .param("productName", "Updated Product")
                .param("productQuantity", "15"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));
    }

    @Test
    void testDeleteProductPost() throws Exception {
        mockMvc.perform(post("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/list"));
    }

    @Test
    void testProductListPage() throws Exception {
        when(service.findAll()).thenReturn(Arrays.asList(productInitializer("1", "Product 1", 10), productInitializer("2", "Product 2", 20)));
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("products"))
                .andExpect(view().name("ProductList"));
    }

    Product productInitializer(String productId, String productName, int productQuantity) {
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductQuantity(productQuantity);
        return product;
    }
}
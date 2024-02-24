package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository repository;

    @InjectMocks
    ProductServiceImpl service;

    @BeforeEach
    void setUp() {
        // This method is empty because there is no specific setup required for each test in this class.
    }

    @Test
    void testCreateProduct() {
        Product product = productInitializer("eb558e9f-1c39-460e-8860-71af6af63bd6", "Sampo Cap Bambang", 100);
        when(repository.create(product)).thenReturn(product);
        Product createdProduct = service.create(product);
        verify(repository, times(1)).create(product);
        assertNotNull(createdProduct);
        assertEquals(createdProduct.getProductId(), product.getProductId());
    }

    @Test
    void testEditProduct() {
        Product product = productInitializer("eb558e9f-1c39-460e-8860-71af6af63bd6", "Sampo Cap Bambang", 100);
        Product dummyProduct = productInitializer("eb558e9f-1c39-460e-8860-71af6af63bd6", "Sampo Cap Budi", 150);
        when(repository.edit(product)).thenReturn(dummyProduct);
        Product editedProduct = service.edit(product);
        verify(repository, times(1)).edit(product);
        assertNotNull(editedProduct);
        assertEquals(editedProduct.getProductId(), dummyProduct.getProductId());
        assertEquals(editedProduct.getProductName(), dummyProduct.getProductName());
        assertEquals(editedProduct.getProductQuantity(), dummyProduct.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product product = productInitializer("eb558e9f-1c39-460e-8860-71af6af63bd6", "Sampo Cap Bambang", 100);
        when(repository.delete(product.getProductId())).thenReturn(product);
        Product deletedProduct = service.delete(product.getProductId());
        verify(repository, times(1)).delete(product.getProductId());
        assertNotNull(deletedProduct);
    }

    @Test
    void testFindAllIfEmpty() {
        when(repository.findAll()).thenReturn(Collections.<Product>emptyList().iterator());
        List<Product> productList = service.findAll();
        verify(repository, times(1)).findAll();
        assertTrue(productList.isEmpty());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = productInitializer("eb558e9f-1c39-460e-8860-71af6af63bd6", "Sampo Cap Bambang", 100);
        service.create(product1);

        Product product2 = productInitializer("123", "Sampo Cap Budi", 10);
        service.create(product2);

        when(repository.findAll()).thenReturn(Arrays.asList(product1, product2).iterator());
        List<Product> productList = service.findAll();
        verify(repository, times(1)).findAll();
        assertEquals(2, productList.size());
    }

    @Test
    void testFindProductById() {
        Product product1 = productInitializer("eb558e9f-1c39-460e-8860-71af6af63bd6", "Sampo Cap Bambang", 100);
        service.create(product1);

        when(repository.findById(product1.getProductId())).thenReturn(product1);
        Product foundProduct = service.findById(product1.getProductId());
        verify(repository, times(1)).findById(product1.getProductId());
        assertEquals(product1, foundProduct);
    }

    Product productInitializer(String productId, String productName, int productQuantity) {
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductQuantity(productQuantity);
        return product;
    }

}

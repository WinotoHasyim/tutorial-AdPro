package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindProductByNonExistentId() {
        Product existingProduct = new Product();
        existingProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        existingProduct.setProductName("Sampo Cap Bambang");
        existingProduct.setProductQuantity(100);
        productRepository.create(existingProduct);

        Product existingProduct2 = new Product();
        existingProduct2.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd7");
        existingProduct2.setProductName("Sampo Cap Budi");
        existingProduct2.setProductQuantity(10);
        productRepository.create(existingProduct2);

        Product nonExistingProduct = productRepository.findProductById("nonexistentId");
        assertNull(nonExistingProduct);
    }

    @Test
    void testCreateNullAndNoNameProduct() {
        Product nullProduct = null;
        assertThrows(IllegalArgumentException.class, () -> productRepository.create(nullProduct));

        Product testProduct = new Product();
        testProduct.setProductId("24");
        testProduct.setProductQuantity(1);
        testProduct.setProductName("");
        assertThrows(IllegalArgumentException.class, () -> productRepository.create(testProduct));
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testCreateEmptyProduct() {
        Product product = new Product();
        assertThrows(IllegalArgumentException.class, () -> productRepository.create(product));

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateProductWithNegativeQuantity() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(-100);
        assertThrows(IllegalArgumentException.class, () -> productRepository.create(product));

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("product");
        product.setProductName("testProduct");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("product");
        updatedProduct.setProductName("updatedProduct");
        updatedProduct.setProductQuantity(50);
        productRepository.edit(updatedProduct);

        Product savedProduct = productRepository.findProductById(updatedProduct.getProductId());
        assertNotNull(savedProduct);
        assertEquals(updatedProduct.getProductName(), savedProduct.getProductName());
        assertEquals(updatedProduct.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testEditEmptyProduct() {
        Product product = new Product();
        product.setProductId("product");
        product.setProductName("testProduct");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId("product");
        assertThrows(IllegalArgumentException.class, () -> productRepository.edit(editedProduct));

        Product originalProduct = productRepository.findProductById(product.getProductId());
        assertNotNull(originalProduct);
        assertEquals(originalProduct.getProductName(), "testProduct");
        assertEquals(originalProduct.getProductQuantity(), 100);
    }

    @Test
    void testEditProductWithNegativeQuantity() {
        Product product = new Product();
        product.setProductId("product");
        product.setProductName("testProduct");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId("product");
        editedProduct.setProductName("testProduct");
        editedProduct.setProductQuantity(-100);
        assertThrows(IllegalArgumentException.class, () -> productRepository.edit(editedProduct));

        Product originalProduct = productRepository.findProductById(product.getProductId());
        assertNotNull(originalProduct);
        assertEquals(originalProduct.getProductQuantity(), 100);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("product");
        product.setProductName("testProduct");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product deletedProduct = productRepository.delete(product.getProductId());
        assertEquals(product.getProductId(), deletedProduct.getProductId());
        assertNull(productRepository.findProductById(product.getProductId()));
    }
}

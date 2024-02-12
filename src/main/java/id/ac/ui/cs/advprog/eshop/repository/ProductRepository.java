package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        validateProduct(product);
        productData.add(product);
        return product;
    }

    public Product edit(Product dummyProduct){
        validateProduct(dummyProduct);
        String productId = dummyProduct.getProductId();
        Product productToEdit = findProductById(productId);
        productToEdit.setProductName(dummyProduct.getProductName());
        productToEdit.setProductQuantity(dummyProduct.getProductQuantity());
        return productToEdit;
    }

    public Product delete(String productId){
        Product productToDelete = findProductById(productId);
        productData.remove(productToDelete);
        return productToDelete;
    }

    public Product findProductById(String productId){
        for(Product iteratedProduct: productData){
            if(iteratedProduct.getProductId().equals(productId)){
                return iteratedProduct;
            }
        }
        return null;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    private void validateProduct(Product product) {
        if (product == null || product.getProductName() == null || product.getProductName().isEmpty() || String.valueOf(product.getProductQuantity()).isEmpty()) {
            throw new IllegalArgumentException("Product should have a name and quantity");
        }
        else if (product.getProductQuantity() < 0) {
            throw new IllegalArgumentException("Product quantity should not be negative");
        }
    }
}

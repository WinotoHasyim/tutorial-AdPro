package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository implements RepositoryInterface<Product>{
    private List<Product> productData = new ArrayList<>();

    @Override
    public Product create(Product product){
        validateObject(product);

        if (product.getProductId() == null) {
            UUID uuid = UUID.randomUUID();
            product.setProductId(uuid.toString());
        }
        productData.add(product);
        return product;
    }

    @Override
    public Product edit(Product updatedProduct){
        validateObject(updatedProduct);

        String productId = updatedProduct.getProductId();
        Product productToEdit = findById(productId);
        productToEdit.setProductName(updatedProduct.getProductName());
        productToEdit.setProductQuantity(updatedProduct.getProductQuantity());
        return productToEdit;
    }

    @Override
    public Product delete(String productId){
        Product productToDelete = findById(productId);
        productData.remove(productToDelete);
        return productToDelete;
    }

    @Override
    public Product findById(String productId){
        for(Product iteratedProduct: productData){
            if(iteratedProduct.getProductId().equals(productId)){
                return iteratedProduct;
            }
        }
        return null;
    }

    @Override
    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    @Override
    public void validateObject(Product product) {
        if (product == null 
        || product.getProductName() == null 
        || product.getProductName().isEmpty()
        ) {
            throw new IllegalArgumentException("Product should have a name and quantity");
        }
        else if (product.getProductQuantity() < 0) {
            throw new IllegalArgumentException("Product quantity should not be negative");
        }
    }
}

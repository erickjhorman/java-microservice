package academy.digitallab.store.product.service;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> listAllProduct();
    Product getProduct(Long id);
    Product createProduct(Product product);
    Product updateProduct (Product product);
    List<Product> findByCategory(Category category);
    Product updateStock(Long id, Double quantity);
    Product deleteProduct(Long id);
}

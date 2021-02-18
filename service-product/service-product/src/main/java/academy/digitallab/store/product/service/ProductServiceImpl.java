package academy.digitallab.store.product.service;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.exception.ProductNotFoundException;
import academy.digitallab.store.product.reporsitory.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl  implements  ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return (productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Not found")));
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("CREATED");
        product.setCreatedAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productDB = productRepository.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException("Not found"));
        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setCategory(product.getCategory());
        productDB.setPrice(product.getPrice());
        productDB.setStatus("Updated");
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product productDB = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Not found"));
        Double stock = productDB.getStock() + quantity;
        productDB.setStock(stock);
        return productRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Not found"));
        productDB.setStatus("DELETED");
        return productRepository.save(productDB);
    }
}

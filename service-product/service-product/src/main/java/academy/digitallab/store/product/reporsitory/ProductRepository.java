package academy.digitallab.store.product.reporsitory;

import academy.digitallab.store.product.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product,Long> {
     List<Product> findByCategory(Category category);
}

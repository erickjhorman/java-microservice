package academy.digitallab.store.shopping.client;

import academy.digitallab.store.shopping.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/products")
@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("/{id}")
     ResponseEntity<Product> getProduct(@PathVariable Long id);

    @PutMapping("/{id}/stock")
     ResponseEntity<?> updatedStockProduct(@PathVariable  Long id,@RequestParam(name = "quantity", required = true) Double quantity);
}

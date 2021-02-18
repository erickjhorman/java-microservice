package academy.digitallab.store.product.bootstrap;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.reporsitory.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class BootrstrapData implements CommandLineRunner {

    private final ProductRepository productRepository;

    public BootrstrapData(ProductRepository productRepository) {this.productRepository = productRepository;}

    @Override
    public void run(String... args) throws Exception {

        log.info("Loading Customer Data");

        Product.builder()
                .id(1l)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createdAt(new Date()).build();

         Product.builder()
                 .id(2l)
                .name("Play Station")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1350.45"))
                .status("Created")
                .createdAt(new Date()).build();

        Product.builder()
                .id(3l)
                .name("GameBoy Advance")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1784.47"))
                .status("Created")
                .createdAt(new Date()).build();
         log.info("Customers Saved: " + productRepository.count());

    }
}

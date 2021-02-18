package academy.digitallab.store.product.reporsitory;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;


@DataJpaTest
 class ProductRepositoryMockTest {

    @Autowired
    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("Should Find A Product By Any Category")
    void shouldFindProductByCategory() {
        Product product01 = Product.builder()
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createdAt(new Date()).build();

        productRepository.save(product01);
        List<Product> founds = productRepository.findByCategory(product01.getCategory());

        Assertions.assertThat(founds.size()).isEqualTo(3);

    }
}
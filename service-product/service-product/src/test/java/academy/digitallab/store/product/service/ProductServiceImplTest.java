package academy.digitallab.store.product.service;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.reporsitory.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    ProductServiceImpl productService;

    @BeforeEach
    public void  setup(){
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);

        /*
        Product computer = Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createdAt(new Date()).build();

        Mockito.when(productRepository.findById(1l))
                .thenReturn(Optional.of(computer));

        Mockito.when(productRepository.save(computer)).thenReturn(computer);
*/
    }

    @Test
    @DisplayName("Should List All Products")
    void shouldListAllProducts(){ }

    @Test
    @DisplayName("Should Find A Product By Id")
    void shouldFindProductById(){
        Product product = Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createdAt(new Date()).build();

        Product expectedCustomerResponse = Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createdAt(new Date()).build();

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Product productResponse = productService.getProduct(1L);

        Assertions.assertThat(productResponse.getId()).isEqualTo(expectedCustomerResponse.getId());
    }

    @Test
    @DisplayName("Should Create A Product")
    void shouldCreateProduct(){
        Product productRequest = Product.builder()
                .id(5L)
                .name("Cellphone")
                .category(Category.builder().id(1L).build())
                .description("Iphone XS")
                .stock(Double.parseDouble("18"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createdAt(new Date()).build();

        Product productResponse = Product.builder()
                .id(5L)
                .name("Cellphone")
                .category(Category.builder().id(1L).build())
                .description("Iphone XS")
                .stock(Double.parseDouble("18"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createdAt(new Date()).build();

        Mockito.when(productRepository.save(productRequest)).thenReturn(productResponse);
        Product product =  productRepository.save(productRequest);
        Mockito.verify(productRepository,Mockito.times(1)).save(ArgumentMatchers.any(Product.class));

        Assertions.assertThat(product.getId()).isEqualTo(5L);
        Assertions.assertThat(product.getName()).isEqualTo("Cellphone");
    }


    @Test
    @DisplayName("Should Update A Product")
    void ShouldUpdateProduct(){
        Product productRequest = Product.builder()
                .id(1L)
                .name("Phone")
                .description("Iphone XS")
                .stock(Double.parseDouble("96"))
                .price(Double.parseDouble("1897.99"))
                .build();

        Product productFound = Product.builder()
                .id(1L)
                .name("Cellphone")
                .category(Category.builder().id(1L).build())
                .description("Iphone XS")
                .stock(Double.parseDouble("18"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createdAt(new Date()).build();

        Product productUpdated = Product.builder()
                .id(1L)
                .name("Phone")
                .category(Category.builder().id(1L).build())
                .description("Iphone XS")
                .stock(Double.parseDouble("96"))
                .price(Double.parseDouble("1897.99"))
                .status("Updated")
                .createdAt(new Date()).build();

        Product productUpdatedResponse = Product.builder()
                .id(1L)
                .name("Phone")
                .category(Category.builder().id(1L).build())
                .description("Iphone XS")
                .stock(Double.parseDouble("96"))
                .price(Double.parseDouble("1897.99"))
                .status("Updated")
                .createdAt(new Date()).build();


        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(productFound));
        Product found = productRepository.findById(1L).get();

        Mockito.when(productRepository.save(productUpdated)).thenReturn(productUpdatedResponse);
        productRepository.save(productUpdated);
        Assertions.assertThat(found.getId()).isEqualTo(1L);
        Assertions.assertThat(productUpdated.getStatus()).isEqualTo("Updated");

    }

    /*
    @Test
    @DisplayName("Should Update Stock And Then Return New Stock")
    void ShouldUpdatedStock(){
        Product found = productService.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("computer");

        Product newStock = productService.updateStock(1L, Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(18.0);


    }

     */
}
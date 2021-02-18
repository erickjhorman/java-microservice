package academy.digitallab.store.product.dto;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Setter
@Getter
@Builder
public class ProductDto {
    private Long id;

    @NotEmpty(message = "El nombre no debe ser vacio")
    private String name;
    private String description;

    @Positive(message = "El stock debe ser mayor que cero")
    private Double stock;
    private Double price;
    private String status;
    private Date createdAt;

    @NotNull(message = "La categoria no puede ser vacia")
    private Category category;

    public Product toEntity(){
        Product product = Product.builder()
                .name(getName())
                .description(getDescription())
                .stock(getStock())
                .price(getPrice())
                .status(getStatus())
                .category(Category.builder().id(getCategory().getId()).name(getCategory().getName()).build()).build();

        return product;

    }


}

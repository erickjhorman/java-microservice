package academy.digitallab.store.product.controller;

import academy.digitallab.store.product.dto.ProductDto;
import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(ProductController.BASE_URL)
public class ProductController {

    public  static final String BASE_URL = "/products";
    private final ProductService  productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId", required = false) Long categoryId ){
        List<Product> products = new ArrayList<>();
         if(null == categoryId){
             products = productService.listAllProduct();
             if(products.isEmpty()){
                 return ResponseEntity.noContent().build();
             }
         } else {
             products = productService.findByCategory(Category.builder().id(categoryId).build());
             if(products.isEmpty()){
                 return ResponseEntity.notFound().build();
             }
         }
         return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product product = productService.getProduct(id);
        if(null == product){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid  @RequestBody ProductDto productDto, BindingResult result){
         if(result.hasErrors()){
             throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
         }
         productService.createProduct(productDto.toEntity());
         return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product =  productDto.toEntity();
        product.setId(id);
        Product productUpdated = productService.updateProduct(product);
        if(null == productUpdated){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(productUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable  Long id){
        Product productDeleted = productService.deleteProduct(id);
        if(null == productDeleted){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<?> updatedStockProduct(@PathVariable  Long id,@RequestParam(name = "quantity", required = true) Double quantity){
        Product productUpdated = productService.updateStock(id,quantity);
        if(null == productUpdated){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String  jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}

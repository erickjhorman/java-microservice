package academy.digitallab.store.product.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message){
        super(message);
    }
}

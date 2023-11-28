package productservice.converter;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import productservice.dto.ProductDto;
import productservice.entity.Product;

@Component
@RequiredArgsConstructor
public class ProductConverter implements Converter<ProductDto, Product> {

    @Override
    public @NonNull Product convert(ProductDto source) {
        return new Product(source.getId(), source.getProductName(), source.getDescription(), source.getCategory(), source.getPrice());
    }

    public @NonNull ProductDto productDto(Product source) {
        return new ProductDto(source.getId(), source.getProductName(), source.getDescription(), source.getCategory(), source.getPrice());
    }
}

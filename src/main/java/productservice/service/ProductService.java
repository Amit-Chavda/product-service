package productservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import productservice.converter.ProductConverter;
import productservice.dto.ProductDto;
import productservice.entity.Product;
import productservice.exception.DuplicateValueExistException;
import productservice.exception.ResourceNotFoundException;
import productservice.repository.ProductRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductDto addProduct(ProductDto productDto) {

        if (productRepository.existsByProductName(productDto.getProductName())) {
            throw new DuplicateValueExistException("Product with name already exist");
        }
        Product product = productRepository.save(productConverter.convert(productDto));
        productDto.setId(product.getId());

        return productDto;
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(productConverter::productDto).toList();
    }

    public ProductDto getProductById(long productId) {
        return productRepository.findById(productId).map(productConverter::productDto)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " not found"));
    }
}

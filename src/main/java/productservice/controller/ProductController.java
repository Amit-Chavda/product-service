package productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import productservice.dto.GenericResponse;
import productservice.dto.ProductDto;
import productservice.service.ProductService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<GenericResponse> addProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(new GenericResponse(true, "Product added successfully", productService.addProduct(productDto), HttpStatus.CREATED.value(), LocalDateTime.now()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GenericResponse> getAllProducts() {
        return new ResponseEntity<>(new GenericResponse(true, "Products returned successfully", productService.getAll(), HttpStatus.OK.value(), LocalDateTime.now()), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GenericResponse> getProductById(@PathVariable long productId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-VALID", "ABC123");
        return new ResponseEntity<>(new GenericResponse(true, "Product returned successfully", productService.getProductById(productId), HttpStatus.OK.value(), LocalDateTime.now()), headers, HttpStatus.OK);
    }
}

package verma.lakshay.assignment.products.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import verma.lakshay.assignment.products.model.Product;
import verma.lakshay.assignment.products.service.ProductService;

import java.util.List;

@Controller
public class ProductConroller {
    private ProductService productService;

    public ProductConroller(ProductService productService) {
        this.productService = productService;

    }

    @PostMapping("v1/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product, UriComponentsBuilder builder) {
        Product productCreated = productService.addProduct(product);
        if (productCreated.getCreatedAt().equals("false")) {
            return new ResponseEntity<Product>(HttpStatus.CONFLICT);
        }

        // HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(builder.path("/v1/product/{id}").buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
    }

    @GetMapping("v1/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> list = productService.getAllProduct();
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }

    @GetMapping("v1/products/search")
    public ResponseEntity<List<Product>> searchProductsByCategory(@RequestParam("category") String category) {
        System.out.println("category" + category);
        List<Product> list = productService.getProductByCategory(category);
        System.out.println(category);
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }
}

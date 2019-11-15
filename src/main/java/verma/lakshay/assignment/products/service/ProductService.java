package verma.lakshay.assignment.products.service;

import org.springframework.stereotype.Service;
import verma.lakshay.assignment.products.model.Product;
import verma.lakshay.assignment.products.repository.ProductRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class ProductService {
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;

    }

    public synchronized Product addProduct(Product product) {
        System.out.println(product);
        List<Product> list = repository.findByNameAndDescription(product.getName(), product.getDescription());
        if (list.size() > 0) {
            product.setCreatedAt("false");
        } else {
            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
            product.setCreatedAt(df.format(new Date()));
            repository.save(product);
        }
        return product;
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        repository.findAll().forEach(e -> {
            list.add(e);
            System.out.println(e);
        });
        return list;
    }
    public List<Product> getProductByCategory(String category){
        List<Product> list = new ArrayList<>();
        repository.findByCategoryOrderByCreatedAtDesc(category).forEach(e -> {
            list.add(e);
            System.out.println("Apparel : " + e);
        });
        System.out.println(list.size());
        return list;
    }
}

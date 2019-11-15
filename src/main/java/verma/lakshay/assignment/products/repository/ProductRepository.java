package verma.lakshay.assignment.products.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import verma.lakshay.assignment.products.model.Product;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, String> {
    public List<Product> findAll();

    List<Product> findByNameAndDescription(String name, String description);

    List<Product> findByCategoryOrderByCreatedAtDesc(Pageable pageable,String category);

}

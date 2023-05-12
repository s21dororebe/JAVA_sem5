package lv.venta.java_sem5.repos;

import lv.venta.java_sem5.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepo extends CrudRepository<Product, Long> {
    boolean existsByTitleAndDescriptionAndPrice(String inputTitle, String inputDescription, float inputPrice);
    Product findByTitleAndDescriptionAndPrice(String inputTitle, String inputDescription, float inputPrice);
}

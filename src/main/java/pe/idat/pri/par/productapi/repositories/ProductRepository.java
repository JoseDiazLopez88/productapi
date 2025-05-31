
package pe.idat.pri.par.productapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.idat.pri.par.productapi.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% AND p.sku LIKE %:sku%")
    Page<Product> findAllWithPagingAndCustomFilter(@Param("name") String name, @Param("sku") String sku, Pageable pageable);
}

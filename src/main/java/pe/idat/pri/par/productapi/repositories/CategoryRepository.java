package pe.idat.pri.par.productapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.idat.pri.par.productapi.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}

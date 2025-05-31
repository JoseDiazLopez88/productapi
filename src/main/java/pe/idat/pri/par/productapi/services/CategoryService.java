package pe.idat.pri.par.productapi.services;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.idat.pri.par.productapi.models.Category;
import pe.idat.pri.par.productapi.repositories.CategoryRepository;
import pe.idat.pri.par.productapi.repositories.ProductRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, 
                           ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Transactional(timeout = 10, readOnly = false)
    public Category insert(Category entity) throws NotFoundException {
        var product = productRepository.findById(entity.getProduct().getId())
            .orElseThrow(() -> new NotFoundException());

        entity.setProduct(product);

        return categoryRepository.save(entity);
    }
}

package pe.idat.pri.par.productapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.pri.par.productapi.models.Category;
import pe.idat.pri.par.productapi.services.CategoryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService service){
        this.categoryService = service;
    }

    @PostMapping()
    public ResponseEntity<Category> insert(@RequestBody Category entity) {
        try {
            var result = categoryService.insert(entity);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

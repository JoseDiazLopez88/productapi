package pe.idat.pri.par.productapi.controllers;

import java.net.URI;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import pe.idat.pri.par.productapi.dtos.*;
import pe.idat.pri.par.productapi.models.Product;
import pe.idat.pri.par.productapi.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")

public class ProductRestController {

    private ProductService productService;

    public ProductRestController(ProductService service){
        this.productService = service;
    }

    @Operation(summary= "Obtener todos los productos", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "productos recuperados correctamente")
    })

    @GetMapping
    public ResponseEntity<List<GetAllProductResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAll());
    }


    @Operation(summary= "Obtener un producto por id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "productos recuperados correctamente"),
            @ApiResponse(responseCode = "400", description = "productos no recuperados")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetProductByIdResponse> getById(@PathVariable Long id) {
        var response = productService.getById(id);
        return response != null ? 
            ResponseEntity.ok(response) : 
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    @Operation(summary= "Obtener los productos por paginas", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "productos recuperados correctamente")
    })
    @GetMapping("/paging")
    public ResponseEntity<BasePageableDto<GetAllProductPageableResponse>> getAllPageable(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "5") int pageSize, 
        @RequestParam(defaultValue = "id") String sortColumn,
        @RequestParam(defaultValue = "asc") String sortOrder, 
        @RequestParam(defaultValue = "") String name, 
        @RequestParam(defaultValue = "") String sku) {
        return ResponseEntity.ok(productService.getAllPageable(pageNumber, pageSize, sortColumn, sortOrder, name, sku));
    }



    @Operation(summary= "Registrar un producto", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "producto registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "producto no ha podido ser registrado")
    })
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product entity){
        try {
            var response = productService.create(entity);
            return ResponseEntity.created(URI.create("/api/v1/products/" + response.getId())).body(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @Operation(summary= "Actualizar un producto", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "producto actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "producto no ha podido ser actualizado")
    })

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product entity) {
        var response = productService.update(id, entity);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(entity);
    }



    @Operation(summary= "Eliminar un producto", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boolean hasRemoved = productService.delete(id);
        return hasRemoved ? 
            ResponseEntity.noContent().build() : 
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateSku(@PathVariable Long id, @RequestBody UpdateProductRequest updateProductRequest) {
        try {
            // Utilizando el UpdateProductRequest para actualizar el SKU
            var product = productService.updateSku(id, updateProductRequest.getCode());
            return product == null ? 
                ResponseEntity.notFound().build() : 
                ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

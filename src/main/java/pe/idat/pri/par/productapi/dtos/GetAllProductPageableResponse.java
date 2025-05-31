package pe.idat.pri.par.productapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.idat.pri.par.productapi.models.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductPageableResponse {
    private Long id;
    private String name;
    private String brand;
    private String code;
    private String description;
    private int categories;

    public static GetAllProductPageableResponse toDto(Product entity){
        return new GetAllProductPageableResponse(
            entity.getId(),
            entity.getName(),
            entity.getBrand(),
            entity.getCode(),
            entity.getDescription(),
            entity.getCategories().size()
        );
    }
}

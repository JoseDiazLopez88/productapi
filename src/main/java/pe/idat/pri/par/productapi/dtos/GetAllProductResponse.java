package pe.idat.pri.par.productapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.idat.pri.par.productapi.models.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductResponse {
    private Long id;
    private String name;
    private String code;

    public static GetAllProductResponse toDto(Product product){
        return new GetAllProductResponse(
            product.getId(), 
            product.getName(),
            product.getCode()
        );
    }
}

package pe.idat.pri.par.productapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.idat.pri.par.productapi.dtos.BasePageableDto;
import pe.idat.pri.par.productapi.dtos.GetAllProductPageableResponse;
import pe.idat.pri.par.productapi.dtos.GetAllProductResponse;
import pe.idat.pri.par.productapi.dtos.GetProductByIdResponse;
import pe.idat.pri.par.productapi.models.Product;
import pe.idat.pri.par.productapi.repositories.ProductRepository;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product create(Product product){
        return productRepository.saveAndFlush(product);
    }

    public Product update(Long id, Product entity){
        var response = productRepository.findById(id)
            .orElse(null);

        if(response == null) return null;

        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setSku(entity.getSku());
        response.setPrice(entity.getPrice());

        productRepository.saveAndFlush(response);
        return response;
    }

    public boolean delete(Long id){
        var response = productRepository.findById(id)
            .orElse(null);

        if(response == null) return false;
        productRepository.deleteById(id);

        return true;
    }

    public List<GetAllProductResponse> getAll(){
        return productRepository.findAll()
            .stream()
            .map(GetAllProductResponse::toDto)
            .collect(Collectors.toList());
    }

    public BasePageableDto<GetAllProductPageableResponse> getAllPageable(int pageNumber, int pageSize, String sortColumn, String sortOrder, String name, String sku){
        Sort sorting = Sort.by(sortOrder.equals("asc") ? Direction.ASC : Direction.DESC, sortColumn);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        return BasePageableDto.toGetAllProductPageableResponse(
            productRepository.findAllWithPagingAndCustomFilter(name, sku, pageable)
        );
    }

    public GetProductByIdResponse getById(Long id){
        var response = productRepository
            .findById(id)
            .orElse(null);
        if(response == null) return null;

        return GetProductByIdResponse.toDto(response);
    }

    @Transactional(readOnly = false, timeout = 10)
    public Product updateSku(Long id, String sku) throws NotFoundException {
        var product = productRepository.findById(id)
            .orElseThrow(() -> new NotFoundException());
        product.setSku(sku);
        productRepository.save(product);
        return product;
    }

}

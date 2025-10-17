package no.gjensidige.product.service;

import no.gjensidige.product.dto.ProductDTO;
import no.gjensidige.product.exception.ProductNotFoundException;
import no.gjensidige.product.entity.Product;
import no.gjensidige.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductService
 * <p>
 * Class responsible of data manipulation between dto and entity
 *
 *
 */

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Product getProduct(Long id) {

        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

    }

    public Product deleteProduct(Long id) {
        Product p = getProduct(id);
        productRepository.delete(p);
        return p;
    }


    public Product createProduct(ProductDTO inputProduct) {

        Product product = convertToEntity(inputProduct);

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDTO inputProduct) {
        if (inputProduct == null) {
            throw new IllegalArgumentException("The input product can not be null.");
        }
        Product existingProduct = getProduct(id);
        modelMapper.map(inputProduct, existingProduct); // Maps new fields from inputproduct to existing product
        productRepository.save(existingProduct);
        return existingProduct;
    }


    public ProductDTO convertToDTO(Product product) {

        return modelMapper.map(product, ProductDTO.class);

    }

    public Product convertToEntity(ProductDTO productDTO) {

        return modelMapper.map(productDTO, Product.class);

    }


}

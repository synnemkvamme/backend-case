package no.gjensidige.product.service;

import no.gjensidige.product.dto.ProductDTO;
import no.gjensidige.product.entity.Product;
import no.gjensidige.product.exception.ProductNotFoundException;
import no.gjensidige.product.repository.ProductRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigInteger;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    public ModelMapper mm = new ModelMapper();

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    ModelMapper modelMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllProducts() {

        Set<String> uniqueNames = new HashSet<>(Arrays.asList("Larry", "Steve", "James"));
        List<Product> productList = new ArrayList<>();
        uniqueNames.forEach(name ->
        {
            Product p = new Product();
            p.setProductName(name);
            productList.add(p);
        });


        when(productRepository.findAll()).thenReturn(productList);

        List<Product> productList1 = productService.getAllProducts();

        verify(productRepository).findAll();

        assertEquals(3, productList1.size());

    }

    @Test
    public void getProduct() {
        Product p = new Product();
        p.setId(1L);
        Optional<Product> op = Optional.of(p);


        when(productRepository.findById(anyLong())).thenReturn(op);

        Product product = productService.getProduct(1l);

        assertEquals(p,product);
    }

    @Test
    public void deleteProduct() {
        Product p = new Product();
        p.setId(1L);
        Optional<Product> op = Optional.of(p);
        when(productRepository.findById(anyLong())).thenReturn(op);

        Product product = productService.deleteProduct(1l);
        verify(productRepository).delete(p);

        assertEquals(p,product);
    }


    @Test(expected = ProductNotFoundException.class)
    public void deleteProductWithException() {
        Optional<Product> op = Optional.empty();

        when(productRepository.findById(anyLong())).thenReturn(op);

        Product product = productService.deleteProduct(10l);

        verify(productRepository).findById(10l);
        fail("Didn't throw not found exception");
    }

    @Test
    public void updateProduct() {
        Product existingProduct = new Product();
        existingProduct.setCategory("Hardware");
        existingProduct.setProductName("Seagate Baracuda 500GB");
        existingProduct.setNumberSold(BigInteger.valueOf(200));
        existingProduct.setUnitPrice(55.50);

        ProductDTO inputProductDTO = new ProductDTO();
        inputProductDTO.setCategory("New Hardware"); // Updated value
        inputProductDTO.setProductName("Seagate Baracuda 500GB");
        inputProductDTO.setNumbersold(BigInteger.valueOf(250)); // Updated value
        inputProductDTO.setPrice(55.50);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(existingProduct));

        doAnswer(invocation -> {
            ProductDTO source = invocation.getArgument(0);
            Product destination = invocation.getArgument(1);

            // Mocking mapping from source to destination
            destination.setCategory(source.getCategory());
            destination.setProductName(source.getProductName());
            destination.setNumberSold(source.getNumberSold());
            destination.setUnitPrice(source.getUnitPrice());

            return null;
        }).when(modelMapper).map(any(ProductDTO.class), any(Product.class));

        Product updatedProduct = productService.updateProduct(1L, inputProductDTO);
        verify(productRepository).findById(anyLong());
        verify(productRepository).save(updatedProduct);

        assertEquals("New Hardware", updatedProduct.getCategory());
        assertEquals("Seagate Baracuda 500GB", updatedProduct.getProductName());
        assertEquals(BigInteger.valueOf(250), updatedProduct.getNumberSold());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateProductWillNullProduct(){
        Optional<Product> op = Optional.empty();
        when(productRepository.findById(anyLong())).thenReturn(op);
        Product product = productService.updateProduct(1l, null);
        fail("Didn't throw illegal argument exception");
    }


    @Test
    public void convertToDTO() {
        Product product = new Product();
        product.setCategory("Hardware");
        product.setProductName("Seagate Baracuda 500GB");
        product.setNumberSold(BigInteger.valueOf(200));
        product.setUnitPrice(55.50);

        when(modelMapper.map(product, ProductDTO.class)).thenReturn(mm.map(product,ProductDTO.class));
        ProductDTO productDTO = productService.convertToDTO(product);
    }

    @Test
    public void convertToEntity() {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory("Hardware");
        productDTO.setProductName("Seagate Baracuda 500GB");
        productDTO.setNumbersold(BigInteger.valueOf(200));
        productDTO.setPrice(55.50);

        when(modelMapper.map(productDTO,Product.class)).thenReturn(mm.map(productDTO,Product.class));
        Product product = productService.convertToEntity(productDTO);

        assertEquals(product.getProductName(),productDTO.getProductName());
        assertEquals(product.getNumberSold(),productDTO.getNumberSold());
        assertEquals(product.getCategory(),productDTO.getCategory());

    }
}
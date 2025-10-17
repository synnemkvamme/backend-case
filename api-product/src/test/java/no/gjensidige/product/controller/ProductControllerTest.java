package no.gjensidige.product.controller;

import no.gjensidige.product.dto.ProductDTO;
import no.gjensidige.product.entity.Product;
import no.gjensidige.product.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;


    @Mock
    private ProductService productService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProducts() {
        Set<String> uniqueNames = new HashSet<>(Arrays.asList("Larry", "Steve", "James"));
        List<Product> productList = new ArrayList<>();
        uniqueNames.forEach(name ->
        {
            Product p = new Product();
            p.setProductName(name);
            productList.add(p);
        });


        when(productService.getAllProducts()).thenReturn(productList);

        List<Product> productList1 = productController.getProducts();

        verify(productService).getAllProducts();

        assertEquals(3, productList1.size());

    }

    @Test
    public void getProduct() {
        Product p = new Product();
        p.setId(1L);

        when(productService.getProduct(1L)).thenReturn(p);

        Product product = productController.getProduct(1L);

        verify(productService).getProduct(1L);
        assertEquals(1L, product.getId().longValue());
    }

    @Test
    public void createProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);

        Product product = new Product();
        product.setId(1L);

        when(productService.createProduct(productDTO)).thenReturn(product);

        Product recievedProduct = productController.createProduct(productDTO);
        verify(productService).createProduct(productDTO);
        assertEquals(1L, recievedProduct.getId().longValue());
    }

    @Test
    public void updateProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);

        Product product = new Product();
        product.setId(1L);

        when(productService.updateProduct(1L, productDTO)).thenReturn(product);

        productController.updateProduct(1L, productDTO);
        verify(productService).updateProduct(1L, productDTO);
    }

    @Test
    public void deleteProduct() {

        Product p = new Product();
        p.setId(1L);

        when(productService.deleteProduct(1L)).thenReturn(p);

        Product product = productController.deleteProduct(1L);

        verify(productService).deleteProduct(1L);

        assertEquals(1L, product.getId().longValue());

    }
}
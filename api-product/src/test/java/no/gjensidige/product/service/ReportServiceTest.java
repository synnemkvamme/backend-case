package no.gjensidige.product.service;

import no.gjensidige.product.entity.Product;
import no.gjensidige.product.model.FinancialReport;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class ReportServiceTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ReportService reportService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFinancialReport() {
        Product productA = new Product();
        productA.setProductName("A");
        productA.setNumberSold(BigInteger.valueOf(10));
        productA.setUnitPrice(100.0);
        productA.setUnitCost(50.0);

        Product productB = new Product();
        productB.setProductName("B");
        productB.setNumberSold(BigInteger.valueOf(5));
        productB.setUnitPrice(200.0);
        productB.setUnitCost(120.0);

        List<Product> products = new ArrayList<>();
        products.add(productA);
        products.add(productB);

        when(productService.getAllProducts()).thenReturn(products);

        FinancialReport report = reportService.getFinancialReport();

        verify(productService).getAllProducts();
        assertNotNull(report.getCreated());

        // Total revenue = (10*100) + (5*200) = 2000
        assertEquals(2000.0, report.getTotalRevenue(), 0.0);

        // Total cost = (10*50) + (5*120) = 1100
        assertEquals(1100.0, report.getTotalCost(), 0.0);

        // Total margin = 2000 - 1100 = 900
        assertEquals(900.0, report.getTotalMargin(), 0.0);

        assertEquals(productA, report.getMostSoldProduct()); // most sold = productA (10)
        assertEquals(productB, report.getLeastSoldProduct()); // least sold = productB (5)

        // productA margin = 10*(100-50) = 500
        // productB margin = 5*(200-120) = 400
        assertEquals(productA, report.getHighestMarginProduct());
        assertEquals(productB, report.getLowestMarginProduct());
    }

    @Test
    public void getFinancialReportWithEmptyProducts() {
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());
        FinancialReport report = reportService.getFinancialReport();

        verify(productService).getAllProducts();
        assertNotNull(report.getCreated()); // Timestamp should still be set
        assertNull(report.getMostSoldProduct());
        assertNull(report.getLeastSoldProduct());
        assertEquals(0.0, report.getTotalRevenue(), 0.0);
        assertEquals(0.0, report.getTotalCost(), 0.0);
        assertEquals(0.0, report.getTotalMargin(), 0.0);
    }
}

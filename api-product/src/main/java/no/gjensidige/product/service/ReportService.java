package no.gjensidige.product.service;

import no.gjensidige.product.entity.Product;
import no.gjensidige.product.model.FinancialReport;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ReportService
 * <p>
 * Class responsible of creating the financial report
 *
 *
 */
@Service
public class ReportService {
    private final ProductService productService;

    public ReportService(ProductService productService) {
        this.productService = productService;
    }

    public FinancialReport getFinancialReport() {
        FinancialReport financialReport = new FinancialReport();
        List<Product> products = productService.getAllProducts();
        financialReport.setCreated(Timestamp.valueOf(LocalDateTime.now()));


        if (products == null || products.isEmpty()) {
            financialReport.setTotalRevenue(0.0);
            financialReport.setTotalCost(0.0);
            financialReport.setTotalMargin(0.0);
            return financialReport;
        }

        setTotalValues(financialReport, products);
        setMostAndLeastSoldInfo(financialReport, products);
        setMarginContributions(financialReport, products);

        return financialReport;
    }

    private void setTotalValues(FinancialReport financialReport, List<Product> products) {
        double totalRevenue = products.stream()
                .mapToDouble(product -> (product.getUnitPrice() * product.getNumberSold().intValue()))
                .sum();

        double totalCost = products.stream()
                .mapToDouble(product -> (product.getUnitCost() * product.getNumberSold().intValue()))
                .sum();

        financialReport.setTotalRevenue(totalRevenue);
        financialReport.setTotalCost(totalCost);
        financialReport.setTotalMargin(totalRevenue - totalCost);
    }

    private void setMostAndLeastSoldInfo(FinancialReport financialReport, List<Product> products) {
        Product mostSold = Collections.max(products, Comparator.comparing(Product::getNumberSold));
        Product leastSold = Collections.min(products, Comparator.comparing(Product::getNumberSold));

        financialReport.setMostSoldProduct(mostSold);
        financialReport.setLeastSoldProduct(leastSold);
    }

    private void setMarginContributions(FinancialReport financialReport, List<Product> products) {
        Product highestMarginProduct = products.stream()
                .max(Comparator.comparing(product -> product.getNumberSold().intValue() * (product.getUnitPrice() - product.getUnitCost())))
                .orElse(null);

        Product lowestMarginProduct = products.stream()
                .min(Comparator.comparing(p -> p.getNumberSold().intValue() * (p.getUnitPrice() - p.getUnitCost())))
                .orElse(null);

        financialReport.setHighestMarginProduct(highestMarginProduct);
        financialReport.setLowestMarginProduct(lowestMarginProduct);
    }
}

package no.gjensidige.product.controller;

import no.gjensidige.product.model.FinancialReport;
import no.gjensidige.product.service.ProductService;
import no.gjensidige.product.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * RestController for the new report endpoint in exercise 2
 *
 */


@RestController
@RequestMapping(name = "Report", value = "reports")
public class ReportController {
    @Autowired
    ReportService reportService;


    /**
     * Todo Create implementation for Financial report
     * as stated in exercise 2.
     *
     */
    @GetMapping(name = "/financial")
    public FinancialReport getFinancialReport() {

        return reportService.getFinancialReport();
    }

}

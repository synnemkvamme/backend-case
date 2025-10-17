package no.gjensidige.product.controller;

import no.gjensidige.product.model.FinancialReport;
import no.gjensidige.product.service.ReportService;
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
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(name = "/financial")
    public FinancialReport getFinancialReport() {

        return reportService.getFinancialReport();
    }

}

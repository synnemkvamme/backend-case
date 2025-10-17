package no.gjensidige.product.controller;

import no.gjensidige.product.model.FinancialReport;
import no.gjensidige.product.service.ReportService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReportControllerTest {

    @InjectMocks
    private ReportController reportController;

    @Mock
    private ReportService reportService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFinancialReport_returnsReport() {
        FinancialReport mockReport = new FinancialReport();
        mockReport.setTotalRevenue(100.0);
        when(reportService.getFinancialReport()).thenReturn(mockReport);

        FinancialReport result = reportController.getFinancialReport();

        verify(reportService).getFinancialReport();
        assertEquals(100.0, result.getTotalRevenue(), 0.0);
    }
}

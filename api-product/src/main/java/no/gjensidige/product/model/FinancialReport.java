package no.gjensidige.product.model;

import no.gjensidige.product.entity.Product;

import java.sql.Timestamp;

/**
 * FinancialReport
 * The model we would like to fill with data in exercise 2
 *
 */

public class FinancialReport {

    Timestamp created;
    Product highestMarginProduct;
    Product lowestMarginProduct;
    Product mostSoldProduct;
    Product leastSoldProduct;
    Double totalRevenue;
    Double totalCost;
    Double totalMargin;

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Product getHighestMarginProduct() {
        return highestMarginProduct;
    }

    public void setHighestMarginProduct(Product highestMarginProduct) {
        this.highestMarginProduct = highestMarginProduct;
    }

    public Product getLowestMarginProduct() {
        return lowestMarginProduct;
    }

    public void setLowestMarginProduct(Product lowestMarginProduct) {
        this.lowestMarginProduct = lowestMarginProduct;
    }

    public Product getMostSoldProduct() {
        return mostSoldProduct;
    }

    public void setMostSoldProduct(Product mostSoldProduct) {
        this.mostSoldProduct = mostSoldProduct;
    }

    public Product getLeastSoldProduct() {
        return leastSoldProduct;
    }

    public void setLeastSoldProduct(Product leastSoldProduct) {
        this.leastSoldProduct = leastSoldProduct;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getTotalMargin() {
        return totalMargin;
    }

    public void setTotalMargin(Double totalMargin) {
        this.totalMargin = totalMargin;
    }
}

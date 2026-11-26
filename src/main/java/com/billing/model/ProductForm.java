package com.billing.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Form data for add/update. Fields are Strings so we can give the same
 * friendly validation messages as the reference project.
 */
public class ProductForm {

    private Integer id;
    private String productName = "";
    private String description = "";
    private String quantity = "";
    private String price = "";

    public static ProductForm from(Product p) {
        ProductForm f = new ProductForm();
        f.id = p.getId();
        f.productName = p.getProductName();
        f.description = p.getDescription();
        f.quantity = String.valueOf(p.getQuantity());
        f.price = p.getPrice().toPlainString();
        return f;
    }

    /** Returns the list of error messages (empty list = valid). Also trims the fields. */
    public List<String> validate() {
        List<String> errors = new ArrayList<>();
        productName = productName == null ? "" : productName.trim();
        description = description == null ? "" : description.trim();
        quantity = quantity == null ? "" : quantity.trim();
        price = price == null ? "" : price.trim();

        if (productName.isEmpty()) {
            errors.add("Product Name cannot be empty.");
        } else if (productName.length() > 100) {
            errors.add("Product Name must be 100 characters or less.");
        }
        if (description.isEmpty()) {
            errors.add("Description cannot be empty.");
        } else if (description.length() > 255) {
            errors.add("Description must be 255 characters or less.");
        }
        if (!quantity.matches("\\d{1,9}") || Integer.parseInt(quantity) <= 0) {
            errors.add("Quantity must be a whole number greater than 0.");
        }
        if (!price.matches("\\d+(\\.\\d{1,2})?")) {
            errors.add("Price must be a valid number greater than 0 (example: 49.99).");
        } else {
            BigDecimal value = new BigDecimal(price);
            if (value.signum() <= 0) {
                errors.add("Price must be a valid number greater than 0 (example: 49.99).");
            } else if (value.compareTo(new BigDecimal("99999999.99")) > 0) {
                errors.add("Price is too large.");
            }
        }
        return errors;
    }

    /** Only call after validate() returned no errors. */
    public Product toProduct() {
        Product p = new Product(productName, description, Integer.parseInt(quantity), new BigDecimal(price));
        p.setId(id);
        return p;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
}

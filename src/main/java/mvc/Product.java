package mvc;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
Product number cannot be empty and should be between 2 and 5 characters
Product name cannot be empty and should be between 2 and 20 characters
* */
public class Product {
    @NotNull
    @Length(min = 2, max= 5)
    private String productNumber;

    @NotEmpty
    @Length(min = 2, max= 20)
    private String name;
    private double price;
    private int quantity;

    public Product(){

    }

    public Product(String productNumber, String name, double price) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
    }


    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        this.quantity--;
    }
}

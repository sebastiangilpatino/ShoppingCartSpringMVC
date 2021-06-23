package mvc;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<Product>();
    }

    public void removeProduct(String productNumber) {
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getProductNumber().equals(productNumber)
                    && products.get(i).getQuantity()==1){
                products.remove(i);
                return;
            }else if(products.get(i).getProductNumber().equals(productNumber)
                    && products.get(i).getQuantity()>1){
                products.get(i).decreaseQuantity();
                return;
            }
        }
    }

    public void addProduct(Product product) {

        for (int i = 0; i < products.size(); i++) {
            if (product.getProductNumber().equals(products.get(i).getProductNumber())) {
                products.get(i).increaseQuantity();
                return;
            }
        }
        product.increaseQuantity();
        products.add(product);
    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < products.size(); i++) {
            total += products.get(i).getPrice();
        }
        return total;
    }

    public List<Product> getProducts() {
        return products;
    }
}

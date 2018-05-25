package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "baskets")
public class Basket {
    private int id;
    private List<Product> basket;
    private Customer customer;



    public Basket(Customer customer) {
        this.basket = new ArrayList<>();
        this.customer = customer;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "basket")
    public List<Product> getProducts() {
        return basket;
    }

    public void setProducts(List<Product> products) {
        this.basket = products;
    }

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int countProductsInBasket(){
        return this.basket.size();
    }

    public void addProducttoBasket(Product product){
        this.basket.add(product);
    }

    public List<Product> basketGivesAllProductsToCustomer(){
        ArrayList<Product> customerProducts = new ArrayList<>();
        customerProducts.addAll(basket);
         return customerProducts;
    }

    public double calculateTotalCostOfAllItemsInBasket(){
        double result = 0;
        for(Product product: basket){
            result += product.getPrice();
        } return result;
    }
}

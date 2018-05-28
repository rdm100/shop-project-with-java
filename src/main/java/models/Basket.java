package models;

import db.DBHelper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "baskets")
public class Basket {
    private int id;
    private List<Product> basket;
    private Customer customer;


    public Basket() {
    }

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

    @OneToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int countProductsInBasket(){
        return this.basket.size();
    }

    public void addProducttoBasket(Product product, Stock stock){
        this.basket.add(product);
        stock.removeProductFromStock(product);
        product.setBasket(this);
        DBHelper.save(product);
        DBHelper.save(this);
        DBHelper.save(stock);
    }

    public void removeProducttoBasket(Product product, Stock stock){
        this.basket.remove(product);
        stock.addProductToStock(product);
        product.setBasket(this);
        DBHelper.save(product);
        DBHelper.save(this);
        DBHelper.save(stock);
    }



    public List<Product> basketGivesAllProductsToCustomer(){
        ArrayList<Product> customerProducts = new ArrayList<>();
        customerProducts.addAll(basket);
        basket.clear();

         return customerProducts;

    }

    public void allBasketItemsGoBackIntoStock(Stock stock){
        stock.addMulitpleThingsToStock(this.basket);
        this.basket.clear();
    }

    public double calculateTotalCostOfAllItemsInBasket(){
        double result = 0;
        for(Product product: basket){
            result += product.getPrice();
        } return result;
    }

    public void updateCustomerOrderFromBasket(Order order){
        order.addBasketProductsToOrder(basketGivesAllProductsToCustomer());
        order.setCustomer(this.customer);
    }
}

package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    private int id;
    private String name;
    private int age;
    private double wallet;
    private List<Product> products;

    public Customer() {
    }

    public Customer(String name, int age, double wallet) {
        this.name = name;
        this.age = age;
        this.wallet = wallet;
        this.products = new ArrayList<Product>();
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

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column
    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    @OneToOne()
    @PrimaryKeyJoinColumn
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int countCustomerHasProducts(){
        return this.products.size();
    }

    public void addBasketToCustomerProducts(List<Product> products){
        this.products.addAll(products);
    }
    public boolean customerCanAffordShopping(Basket basket){
    if(getWallet() > basket.calculateTotalCostOfAllItemsInBasket())
    {return true;}
    return false;
    }

    public double customerPaysForBasket(Basket basket){
        double total = basket.calculateTotalCostOfAllItemsInBasket();
        if(customerCanAffordShopping(basket)){
            setWallet(getWallet() - basket.calculateTotalCostOfAllItemsInBasket());
            return  total;
        } return 0;
    }
}

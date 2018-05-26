package models;

import db.DBHelper;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
    private int id;
    private String name;
    private double price;
    private Basket basket;
    private Stock stock;
    private List<Order> orders;

    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.orders = new ArrayList<>();

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
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name ="basket_id")
    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name ="stock_id")
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "products_in_order",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")}
    )
    @Fetch(FetchMode.SELECT)
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrdertoProduct(Order order){
        this.orders.add(order);
        DBHelper.save(this);

    }
}

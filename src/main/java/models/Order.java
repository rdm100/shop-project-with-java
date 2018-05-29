package models;

import db.DBHelper;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    private int id;
    private List<Product> boughtProducts;
    private Customer customer;

    public Order() {
    }

    public Order(List<Product> boughtProducts, Customer customer) {
        this.boughtProducts = boughtProducts;
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

    @ManyToMany(mappedBy = "orders")
    @Fetch(FetchMode.SELECT)
    public List<Product> getProducts() {
        return boughtProducts;
    }


    public void setProducts(List<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }


    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name ="customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Column
    public int countOftItemsInOrder(){
       return this.boughtProducts.size();
    }

    public void addBasketProductsToOrder(List<Product> customersBoughtProducts){
        this.boughtProducts.addAll(customersBoughtProducts);
    }

    public void giveProductsToAnOrder(){
        DBHelper.save(this);
        for (Product product: this.boughtProducts){
            product.addOrdertoProduct(this);

        }

    }

    @Column(name ="order_total")
    public double orderTotal(){
        double total = 0;
        for (Product product: this.boughtProducts){
            total += product.getPrice();
        } return total;
    }



}

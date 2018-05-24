package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "product")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

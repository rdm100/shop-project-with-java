package models;

import db.DBHelper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stock")
public class Stock {
    private int id;
    private List<Product> storeStockList;


    public Stock() {
        this.storeStockList = new ArrayList<>();

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

    @OneToMany(mappedBy = "stock", fetch = FetchType.EAGER)
    public List<Product> getStoreStockList() {
        return storeStockList;
    }

    public void setStoreStockList(List<Product> storeStockList) {
        this.storeStockList = storeStockList;
    }

    public int stockListCount(){
        return this.storeStockList.size();
    }

    public void addProductToStock(Product product){
        this.storeStockList.add(product);
        product.setStock(this);
    }

    public void removeProductFromStock(Product product){
        this.storeStockList.remove(product);
    }

    public int getQuantityOfProduct(Product productToSearch){
        int quantity = 0;
        for(Product product: this.storeStockList ){
            if(product == productToSearch){
                quantity += 1;
            }
        } return quantity;
    }

    public void addMulitpleThingsToStock(List<Product> products){
        this.storeStockList.addAll(products);
    }
}

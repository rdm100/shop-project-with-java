package models;

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
    }
}

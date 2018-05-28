package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "stock")
public class Stock {
    private int id;
    private Map<Product, Integer> storeStockList;


    public Stock() {
        this.storeStockList = new HashMap<>();

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

    @ElementCollection
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "stock_of_product")
    public Map<Product, Integer> getStoreStockList() {
        return storeStockList;
    }

    public void setStoreStockList(Map<Product, Integer> storeStockList) {
        this.storeStockList = storeStockList;
    }


    public int stockListCount(){
        return this.storeStockList.size();
    }

    public void addProductToStock(Product product){
        if(storeStockList.containsKey(product)){
            int value = storeStockList.get(product);

            storeStockList.put(product, value += 1);
        }
        else this.storeStockList.put(product, 1);
    }


}

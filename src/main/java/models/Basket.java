package models;

import db.DBHelper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "basket", fetch = FetchType.EAGER)
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

    public void addProducttoBasket(Product product){
        this.basket.add(product);
        product.setBasket(this);
        DBHelper.save(product);


    }

    public void removeProducttoBasket(Product product){
        this.basket.remove(product);
        product.setBasket(this);
        DBHelper.save(product);
        DBHelper.save(this);

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

    public double discountAmount() {
        if (calculateTotalCostOfAllItemsInBasket() > 50.00) {
            return 10;
        }
        double twoForOneDiscount = 0;
        List<Product> cloned = this.basket.stream().distinct().collect(Collectors.toList());
        for (Product product : cloned) {
           int quantity = getQuantityOfProductInBasket(product);

            if(quantity == 2 ){
                twoForOneDiscount += product.getPrice();

            }
            if(quantity == 3 ){
                twoForOneDiscount += product.getPrice();

            }
            if(quantity == 4 ){
                twoForOneDiscount += (product.getPrice() * 2);

            }
             if(quantity == 5 ){
                twoForOneDiscount += (product.getPrice() * 2);

            }
            if(quantity > 6 ){
                twoForOneDiscount += (product.getPrice() * 3);

            }

           }return twoForOneDiscount;

        }



    public int getQuantityOfProductInBasket(Product productToSearch){
        int quantity = 0;
        for(Product product: this.basket ){
            if(product == productToSearch){
                quantity += 1;
            }
        } return quantity;
    }

    public double giveTotal(){
       double result = 0;
        result += calculateTotalCostOfAllItemsInBasket() ;
        result -= discountAmount();
        return result;
    }

    public void clearBasket(){
        this.basket.clear();
    }
}

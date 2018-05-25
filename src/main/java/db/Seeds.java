package db;


import db.DBHelper;
import models.*;


import java.util.Calendar;
import java.util.List;

public class Seeds {
    public static void seedData() {
        DBHelper.deleteAll(Customer.class);
        DBHelper.deleteAll(Product.class);
        DBHelper.deleteAll(Till.class);
        DBHelper.deleteAll(Stock.class);




        Customer customer = new Customer("Paul", 33, 100);
        Customer customer2 = new Customer("Roberto", 39, 20);


        Calendar bestBefore = Calendar.getInstance();
        bestBefore.set(Calendar.YEAR, 2018);
        bestBefore.set(Calendar.MONTH, 10);
        bestBefore.set(Calendar.DAY_OF_MONTH, 16);

        Product drink = new Drink("Coke", 0.50, 330, 50.0, 0.05 , 40);
        Product drink2 = new Drink("Pespi", 0.50, 330, 50.0, 0.05 , 40);
        Product clothing = new Clothing ("Levis", 100, "M", "Black", "Mens");
        Product electrical = new Electrical("Laptop", 1000, "Dell XPS", "Silver");
        Product food = new Food("Bread", 100, bestBefore, "Scotland", 100);


        Stock stock = new Stock();


        Till till = new Till(100);


        Basket basket = new Basket(customer);
        Basket basket2 = new Basket(customer2);
        basket.addProducttoBasket(food);

        customer.setBasket(basket);
        customer2.setBasket(basket2);

        Order order = new Order(basket.basketGivesAllProductsToCustomer(), customer);





        DBHelper.save(drink);
        DBHelper.save(drink2);
        DBHelper.save(clothing);
        DBHelper.save(electrical);
        DBHelper.save(food);
        DBHelper.save(stock);
        DBHelper.save(till);

        DBHelper.save(customer);
        DBHelper.save(customer2);
        DBHelper.save(basket);
        DBHelper.save(basket2);
        DBHelper.save(order);

        List<Order> orders = DBCustomer.AllOrdersBelongingToACustomer(customer);
        List<Product> products = DBOrder.AllProductsBelongingToAOrder(order);
   }



}

package db;


import db.DBHelper;
import models.*;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Seeds {
    public static void seedData() {
        DBHelper.deleteAll(Customer.class);
        DBHelper.deleteAll(Product.class);
        DBHelper.deleteAll(Till.class);
        DBHelper.deleteAll(Basket.class);
        DBHelper.deleteAll(Order.class);


        Customer customer = new Customer("paulkelly20", "Paul", 33, 100);
        Customer customer2 = new Customer("rdm", "Roberto", 39, 20);
        Customer manager = new Customer("manager", "manager", 40, 10);


        Calendar bestBefore = Calendar.getInstance();
        bestBefore.set(Calendar.YEAR, 2018);
        bestBefore.set(Calendar.MONTH, 10);
        bestBefore.set(Calendar.DAY_OF_MONTH, 16);
        Product drink = new Drink("Coke", 0.50,3 ,  330, 50.0, 0.05, 40);
        Product drink2 = new Drink("Pespi", 0.50,4,  330, 50.0, 0.05, 40);
        Product clothing = new Clothing("Levis", 100,4,  "M", "Black", "Mens");
        Product electrical = new Electrical("Laptop", 1000, 5,  "Dell XPS", "Silver");
        Product food = new Food("Bread", 100, 10,  bestBefore, "Scotland", 100);




        Till till = new Till(100);








        DBHelper.save(drink);
        DBHelper.save(drink2);
        DBHelper.save(clothing);
        DBHelper.save(electrical);
        DBHelper.save(food);

        DBHelper.save(till);

        DBHelper.save(customer);
        DBHelper.save(customer2);
        DBHelper.save(manager);

        customer.getBasket().addProducttoBasket(drink);
        DBHelper.save(customer);
        DBHelper.save(customer.getBasket());
//        basket.addProducttoBasket(food);
//        basket.addProducttoBasket(electrical);
//        double total = basket.giveTotal();
//        Order order = new Order(basket.getProducts(), customer, total);
//        DBHelper.save(order);
//        order.giveProductsToAnOrder();
//        DBHelper.save(food);





//        Order foundOrder = DBHelper.find(Order.class, order.getId());
        Product foundProduct = DBHelper.find(Product.class, food.getId());

//        List<Order> orders = DBCustomer.AllOrdersBelongingToACustomer(customer);
        List<Product> productsFromBasket = DBBasket.AllProductsInABasket(customer.getBasket());
//        List<Product> productsFromOrder = DBOrder.findProductsInOrder(order);
        Customer foundCustomerByUserName = DBCustomer.findCustomerByUsername("paulkelly20");
        List<Product> searchedProducts = DBProduct.productsFromSearch("co");
        List<Product> allProducts = DBHelper.getAll(Product.class);
        List<Basket> baskets = DBHelper.getAll(Basket.class);

        Customer manager22 = DBCustomer.findCustomerByUsername("manager");




    }

}

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
        DBHelper.deleteAll(Basket.class);
        DBHelper.deleteAll(Order.class);


        Customer customer = new Customer("paulkelly20", "Paul", 33, 100);
        Customer customer2 = new Customer("rdm", "Roberto", 39, 20);


        Calendar bestBefore = Calendar.getInstance();
        bestBefore.set(Calendar.YEAR, 2018);
        bestBefore.set(Calendar.MONTH, 10);
        bestBefore.set(Calendar.DAY_OF_MONTH, 16);

        Product drink = new Drink("Coke", 0.50, 330, 50.0, 0.05, 40);
        Product drink2 = new Drink("Pespi", 0.50, 330, 50.0, 0.05, 40);
        Product clothing = new Clothing("Levis", 100, "M", "Black", "Mens");
        Product electrical = new Electrical("Laptop", 1000, "Dell XPS", "Silver");
        Product food = new Food("Bread", 100, bestBefore, "Scotland", 100);


        Stock stock = new Stock();


        Till till = new Till(100);


        Basket basket = new Basket(customer);
        Basket basket2 = new Basket(customer2);


        customer.setBasket(basket);
        customer2.setBasket(basket2);


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
        basket.addProducttoBasket(food, stock);
        basket.addProducttoBasket(electrical, stock);
        Order order = new Order(basket.basketGivesAllProductsToCustomer(), customer);
        DBHelper.save(order);
        order.giveProductsToAnOrder();
        DBHelper.save(food);
        DBHelper.save(electrical);

        stock.addProductToStock(food);
        DBHelper.save(food);
        stock.addProductToStock(electrical);
        DBHelper.save(electrical);



        Basket foundBasket = DBHelper.find(Basket.class, basket.getId());
        Order foundOrder = DBHelper.find(Order.class, order.getId());
        Product foundProduct = DBHelper.find(Product.class, food.getId());

        List<Order> orders = DBCustomer.AllOrdersBelongingToACustomer(customer);
        List<Product> productsFromBasket = DBBasket.AllProductsInABasket(basket);
        List<Product> productsFromOrder = DBOrder.findProductsInOrder(order);
        Stock Findingstock =DBHelper.find(Stock.class, stock.getId() );
        List<Product> productsFromStock = DBStock.AllProductsBelongingToStock(stock);
        Customer foundCustomerByUserName = DBCustomer.findCustomerByUsername("rdm");
    }

}

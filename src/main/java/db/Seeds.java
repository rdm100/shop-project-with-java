package db;


import models.*;

public class Seeds {
    public static void seedData() {
        DBHelper.deleteAll(Customer.class);
        DBHelper.deleteAll(Product.class);
        DBHelper.deleteAll(Till.class);

        Customer customer = new Customer("Paul", 33, 100);
        Customer customer2 = new Customer("Roberto", 28, 1000);
        DBHelper.save(customer);
        DBHelper.save(customer2);

        Product product = new Drink("Coke", 0.50, 4, 330, 4.5, 0, 20 );
        Product product2 = new Drink("Pepsi", 0.65, 4, 330, 4.5, 0, 20 );


    }



}

package controllers;

import db.DBBasket;
import db.DBCustomer;
import db.DBHelper;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.post;

public class BasketController {

    public BasketController() {
        this.setupEndpoints();
    }

    LoginController loginController = new LoginController();


    private void setupEndpoints() {
        get("/basket", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            Customer customer = DBCustomer.findCustomerByUsername(loggedInUser);
            List<Product> products = DBBasket.AllProductsInABasket(customer.getBasket());
            Basket basket = customer.getBasket();
            model.put("basket", basket);
            model.put("products", products);
            model.put("template", "templates/basket/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());



        post("/basket/buy", (req, res) -> {
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            Customer customer = DBCustomer.findCustomerByUsername(loggedInUser);
            Basket customersBasket = customer.getBasket();
            double amountPaid = customer.getBasket().giveTotal();
            Order order = new Order(customersBasket.getProducts(), customer, amountPaid);
            customer.customerPaysForBasket(customersBasket);
            DBHelper.save(order);
            customersBasket.clearBasket();
            DBHelper.save(customersBasket);
            DBHelper.save(customer);

            res.redirect("/account");
            return null;
        }, new VelocityTemplateEngine());
    }


}



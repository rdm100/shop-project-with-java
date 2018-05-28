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
            model.put("products", products);
            model.put("template", "templates/basket/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());



        post("/basket/buy", (req, res) -> {
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            Customer customer = DBCustomer.findCustomerByUsername(loggedInUser);
            Basket customersBasket = customer.getBasket();
            customer.customerCanAffordShopping(customersBasket);
            customer.customerPaysForBasket(customersBasket);
            customersBasket.basketGivesAllProductsToCustomer();
            Order order = new Order(customersBasket.basketGivesAllProductsToCustomer(), customer);

            res.redirect("/shop");
            return null;
        }, new VelocityTemplateEngine());
    }


}



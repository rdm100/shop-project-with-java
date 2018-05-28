package controllers;

import db.DBBasket;
import db.DBCustomer;
import db.DBHelper;
import models.Clothing;
import models.Customer;
import models.Product;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class BasketController {

    public BasketController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {
        get("/basket", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            Customer customer = DBCustomer.findCustomerByUsername(loggedInUser);
            List<Product> products = DBBasket.AllProductsInABasket(customer.getBasket());
            model.put("products", products);
            model.put("template", "templates/clothes/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}

package controllers;

import db.DBCustomer;
import db.DBHelper;
import models.Customer;
import models.Food;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class CustomerController {


    public CustomerController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/account", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            Customer customer = DBCustomer.findCustomerByUsername(loggedInUser);
            model.put("customer", customer);
            model.put("template","templates/customer/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}


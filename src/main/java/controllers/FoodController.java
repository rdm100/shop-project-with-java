package controllers;


import db.DBCustomer;
import db.DBHelper;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class FoodController {

    public FoodController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/foods", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            List<Food> foods = DBHelper.getAll(Food.class);
            model.put("foods", foods);
            model.put("template", "templates/foods/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/foods/:id/buy", (req, res) -> {
            String strId = req.params("id");
            Integer intId = Integer.parseInt(strId);
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            Product product = DBHelper.find(Product.class, intId);
            Customer customer = DBCustomer.findCustomerByUsername(loggedInUser);
            if (customer.getBasket() == null) {
                Basket basket = new Basket(customer);
                customer.setBasket(basket);
                basket.setCustomer(customer);
                DBHelper.save(customer);
                DBHelper.save(basket);
            }
            if (customer.getBasket().getCustomer() == null) {
                customer.getBasket().setCustomer(customer);
                DBHelper.save(customer.getBasket());
            }
            customer.getBasket().addProducttoBasket(product);
            res.redirect("/basket");
            return null;
        }, new VelocityTemplateEngine());


        post("/foods/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Drink drink = DBHelper.find(Food.class, id);
            DBHelper.delete(drink);
            res.redirect("/foods");
            return null;
        }, new VelocityTemplateEngine());

    }

}
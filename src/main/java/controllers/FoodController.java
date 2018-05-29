package controllers;


import controllers.LoginController;
import db.DBCustomer;
import db.DBHelper;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

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
            model.put("template","templates/foods/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/foods/:id/edit", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Food food = DBHelper.find(Product.class, intId);
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("food", food);
            model.put("template", "templates/foods/edit.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get ("/foods/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("template", "templates/foods/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("/foods/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Food food = DBHelper.find(Product.class, intId);
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("food", food);
            model.put("template","templates/foods/show.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());



        post("/foods/:id/buy", (req, res) -> {
            String strId = req.params("id");
            Integer intId = Integer.parseInt(strId);
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            Product product = DBHelper.find(Product.class, intId);
            Customer customer = DBCustomer.findCustomerByUsername(loggedInUser);
            if(customer.getBasket() == null){
                Basket basket = new Basket(customer);
                customer.setBasket(basket);
                basket.setCustomer(customer);
                DBHelper.save(customer);
                DBHelper.save(basket);
            }
            if(customer.getBasket().getCustomer() == null){
                customer.getBasket().setCustomer(customer);
                DBHelper.save(customer.getBasket());
            }
            customer.getBasket().addProducttoBasket(product);
            res.redirect("/basket");
            return null;
        }, new VelocityTemplateEngine());

        post ("/foods/:id", (req, res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Food food = DBHelper.find(Food.class, intId);
            String name = req.queryParams("name");
            double price = Double.parseDouble(req.queryParams("price"));
            String bestBefore = req.queryParams("bestBefore");
            String pattern = "dd-MM-yyyy";
            Date date = new SimpleDateFormat(pattern).parse(bestBefore, new ParsePosition(0));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            String origin = req.queryParams("origin");
            int calories = Integer.parseInt(req.queryParams("calories"));
            food.setName(name);
            food.setPrice(price);
            food.setBestBefore(calendar);
            food.setOrigin(origin);
            food.setCalories(calories);
            DBHelper.save(food);
            res.redirect("/stock");
            return null;

        },new VelocityTemplateEngine());

        post ("/foods/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Food food = DBHelper.find(Food.class, id);
            DBHelper.delete(food);
            res.redirect("/foods");
            return null;
        }, new VelocityTemplateEngine());



        post ("/foods", (req, res) ->{
            Stock stock = (Stock)DBHelper.getAll(Stock.class).get(0);
            String name = req.queryParams("name");
            double price = Double.parseDouble(req.queryParams("price"));
            String bestBefore = req.queryParams("bestBefore");
            String pattern = "dd-MM-yyyy";
            Date date = new SimpleDateFormat(pattern).parse(bestBefore, new ParsePosition(0));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            String origin = req.queryParams("origin");
            int calories = Integer.parseInt(req.queryParams("calories"));
            Food food = new Food(name, price,stock,  calendar, origin, calories);
            DBHelper.save(food);
            res.redirect("/stock");
            return null;


        },new VelocityTemplateEngine());

    }
}
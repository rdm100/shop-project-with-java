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

public class ElectronicsController {

    public ElectronicsController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/electronics", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            List<Electrical> electronics = DBHelper.getAll(Electrical.class);
            model.put("electronics", electronics);
            model.put("template","templates/electronics/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("/electronics/:id/edit", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);

            Electrical electrical = DBHelper.find(Product.class, intId);
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("electrical", electrical);
            model.put("template", "templates/electronics/edit.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get ("/electronics/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("template", "templates/electronics/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("/electronics/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Electrical electrical = DBHelper.find(Product.class, intId);
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("electrical", electrical);
            model.put("template","templates/electronics/show.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());



        post("/electronics/:id/buy", (req, res) -> {
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

        post ("/electronics/:id", (req, res) ->{
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Electrical electrical = DBHelper.find(Electrical.class, intId);
            String name = req.queryParams("name");
            double price = Double.parseDouble(req.queryParams("price"));
            String model = req.queryParams("model");
            String colour = req.queryParams("colour");
            electrical.setName(name);
            electrical.setPrice(price);
            electrical.setModel(model);
            electrical.setColour(colour);
            DBHelper.save(electrical);
            res.redirect("/stock");
            return null;

        },new VelocityTemplateEngine());

        post ("/electronics/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Electrical electrical = DBHelper.find(Electrical.class, id);
            DBHelper.delete(electrical);
            res.redirect("/electronics");
            return null;
        }, new VelocityTemplateEngine());



        post ("/electronics", (req, res) ->{
            Stock stock = (Stock)DBHelper.getAll(Stock.class).get(0);

            String name = req.queryParams("name");
            double price = Double.parseDouble(req.queryParams("price"));
            String model = req.queryParams("model");
            String colour = req.queryParams("colour");
            Electrical electrical = new Electrical(name, price,stock,  model, colour);
            DBHelper.save(electrical);
            res.redirect("/stock");
            return null;


        },new VelocityTemplateEngine());

    }
}
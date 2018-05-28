package controllers;

import db.DBHelper;
import models.Clothing;
import models.Drink;
import models.Product;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class DrinksController {

    public DrinksController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/drinks/:id/edit", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);

            Drink drink = DBHelper.find(Product.class, intId);
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("drink", drink);
            model.put("template", "templates/drinks/edit.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/drinks", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            List<Drink> drinks = DBHelper.getAll(Drink.class);
            model.put("drinks", drinks);
            model.put("template","templates/drinks/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());



    get("/drinks/:id", (req, res) -> {
        String strId = req.params(":id");
        Integer intId = Integer.parseInt(strId);
        Drink drink = DBHelper.find(Product.class, intId);
        Map<String, Object> model = new HashMap<>();
        String loggedInUser = LoginController.getLoggedInUserName(req, res);
        model.put("user", loggedInUser);
        model.put("drink", drink);
        model.put("template","templates/drinks/show.vtl");

        return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

//        post ("/departments/:id", (req, res) -> {
//            String strId = req.params(":id");
//            Integer intId = Integer.parseInt(strId);
//            Depart department = DBHelper.find(intId, Department.class);
//            String title = req.queryParams("title");
//            department.setTitle(title);
//            DBHelper.update(department);
//            res.redirect("/departments");
//            return null;
//
//        }, new VelocityTemplateEngine());
}}



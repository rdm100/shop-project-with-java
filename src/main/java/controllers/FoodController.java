package controllers;


import db.DBHelper;
import models.Electrical;
import models.Food;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

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
    }

}
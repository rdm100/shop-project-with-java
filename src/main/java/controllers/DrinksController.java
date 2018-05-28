package controllers;

import db.DBHelper;
import models.Clothing;
import models.Drink;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class DrinksController {

    public DrinksController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/drinks", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            List<Drink> drinks = DBHelper.getAll(Drink.class);
            model.put("drinks", drinks);
            model.put("template","templates/drinks/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }

}

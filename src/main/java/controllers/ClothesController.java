package controllers;

import db.DBHelper;
import models.Clothing;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class ClothesController {

    public ClothesController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/clothes", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
//            String loggedInUser = LoginController.getLoggedInUserName(req, res);
//            model.put("user", loggedInUser);
            List<Clothing> clothes = DBHelper.getAll(Clothing.class);
            model.put("clothes", clothes);
            model.put("template", "templates/clothes/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}
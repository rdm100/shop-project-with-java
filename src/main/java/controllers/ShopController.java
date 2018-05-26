package controllers;

import db.DBHelper;
import db.Seeds;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class ShopController {

    public static void main(String[] args) {
        Seeds.seedData();
        staticFileLocation("/public");

        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

        get("/shop", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/shop/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);
    }

    }

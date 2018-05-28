package controllers;

import db.DBHelper;
import db.DBStock;
import db.Seeds;
import models.Clothing;
import models.Food;
import models.Product;
import models.Stock;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class StockController {

    public StockController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

    {

        get("/stock", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            List<Product> products = DBHelper.getAll(Product.class);
            model.put("products", products);
            model.put("Product", Product.class);
            model.put("user", loggedInUser);
            model.put("template", "templates/stock/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        },  new VelocityTemplateEngine());
    }
}
}


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
        LoginController loginController = new LoginController();
        ClothesController clothesController = new ClothesController();
        DrinksController drinksController = new DrinksController();
        ElectronicsController electronicsController = new ElectronicsController();
        FoodController foodController = new FoodController();
        StockController stockController = new StockController();

        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();
        get("/shop", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("template", "templates/shop/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);
    }
}

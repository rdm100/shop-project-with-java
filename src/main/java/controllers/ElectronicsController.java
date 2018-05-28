package controllers;



import db.DBHelper;
import models.Drink;
import models.Electrical;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class ElectronicsController {

    public ElectronicsController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/electronics", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
//            String loggedInUser = LoginController.getLoggedInUserName(req, res);
//            model.put("user", loggedInUser);
            List<Electrical> electronics = DBHelper.getAll(Electrical.class);
            model.put("electronics", electronics);
            model.put("template","templates/electronics/index.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }


}

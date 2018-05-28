package controllers;
import db.DBHelper;
import models.Customer;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.get;
import static spark.Spark.post;
public class LoginController {
    public LoginController() {
        this.setupEndpoints();
    }
    private void setupEndpoints() {
        get ("/login", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "templates/login.vtl");
        }, new VelocityTemplateEngine());

        post("/login", (req, res) -> {
            String username = req.queryParams("username");
            req.session().attribute("username", username);
            String name = req.queryParams("name");
            int age = Integer.parseInt(req.queryParams("age"));
            double wallet = Double.parseDouble(req.queryParams("wallet"));
            Customer customer = new Customer(username, name, age, wallet);
            DBHelper.save(customer);
            res.redirect("/shop");
            return null;
        }, new VelocityTemplateEngine());

        get ("/logout", (req, res) -> {
            req.session().removeAttribute("username");
            res.redirect("/shop");
            return null;
        }, new VelocityTemplateEngine());
    }
    public static String getLoggedInUserName(Request req, Response res) {
        String username = req.session().attribute("username");
        if (username == null || username.isEmpty()) {
            res.redirect("/login");
        }
        return username;
    }
}

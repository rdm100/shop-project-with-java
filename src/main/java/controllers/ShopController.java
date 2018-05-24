package controllers;

import db.DBHelper;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class ShopController {

    public static void main(String[] args) {
        staticFileLocation("/public");

        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

        get("/hello", (req, res) -> {
            return "Hello, World!";
        });


    }}

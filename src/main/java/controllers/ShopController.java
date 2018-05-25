package controllers;

import db.Seeds;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class ShopController {

    public static void main(String[] args) {
        Seeds.seedData();
        staticFileLocation("/public");

        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();

        get("/hello", (req, res) -> {
            return "Hello, World!";
        });


    }}

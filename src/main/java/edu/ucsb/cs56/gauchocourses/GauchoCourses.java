package edu.ucsb.cs56.gauchocourses;

import static spark.Spark.port;

import org.apache.log4j.Logger;


import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Simple example of using Mustache Templates
 *
 */

public class GauchoCourses {

	public static final String CLASSNAME="GauchoCourses";
	
	public static final Logger log = Logger.getLogger(CLASSNAME);

	public static void main(String[] args) {

        port(getHerokuAssignedPort());
		
		Map map = new HashMap();
        map.put("name", "Fuheng");
		
        // hello.mustache file is in resources/templates directory
        get("/", (rq, rs) -> new ModelAndView(map, "hello.mustache"), new MustacheTemplateEngine());

		get("/makeSchedule", (rq, rs) -> new ModelAndView(map, "makeSchedule.mustache"), new MustacheTemplateEngine());

		get("/listCourse", (rq, rs) -> new ModelAndView(map, "listCourse.mustache"), new MustacheTemplateEngine());
		
	}
	
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

	
}

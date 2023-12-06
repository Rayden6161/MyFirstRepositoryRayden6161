package com.example.web.routes;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;

public class NewAuthorFormRoute implements TemplateViewRoute {

    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        return new ModelAndView(new HashMap<>(), "new_author");
    }
}

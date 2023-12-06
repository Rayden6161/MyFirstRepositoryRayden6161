package com.example.web.routes;

import com.example.dao.AuthorDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;

public class AuthorDashboardRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        data.put("authors", AuthorDAO.all());
        return new ModelAndView(data, "author_dashboard");
    }
}

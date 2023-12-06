package com.example.web.routes;

import com.example.dao.AuthorDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;

public class AuthorDeleteRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        int author_id = Integer.parseInt(request.params("author_id"));
        boolean isDeleted = AuthorDAO.delete(author_id);

        if(isDeleted) {
            data.put("status", "success");
            data.put("message", "Author successfully deleted!");
        } else {
            data.put("status", "error");
            data.put("message", "Error deleting author.");
        }

        return new ModelAndView(data, "operation_status");
    }
}

package com.example.web.routes;

import com.example.dao.AuthorDAO;
import com.example.models.Author;
import com.example.utils.Base64Utils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;

public class EditAuthorFormRoute implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        int author_id = Integer.parseInt(request.params("author_id"));
        Author author = AuthorDAO.one(author_id);
        HashMap<String, Object> data = new HashMap<>();
        if(author.getPhoto() != null) {
            data.put("photo", Base64Utils.fromBase64Bytes(author.getPhoto()));
        }
        data.put("author", author);
        return new ModelAndView(data, "edit_author");
    }
}

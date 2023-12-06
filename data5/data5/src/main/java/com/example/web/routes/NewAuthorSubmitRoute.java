package com.example.web.routes;

import com.example.dao.AuthorDAO;
import com.example.models.Author;
import com.example.utils.Base64Utils;
import net.coobird.thumbnailator.Thumbnails;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import javax.servlet.MultipartConfigElement;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.HashMap;

public class NewAuthorSubmitRoute implements TemplateViewRoute {
    //bili smo u java spark i pronasli da nam treba query params zato i pravimo ovu rutu
    //da bi kreirao novog autora moramo se sluziti podacima a sve nam dolazi iz query paramsa
//zbog implementacije TemplateViewRoute imamo implementaciju metoda ispod sa parametrima
    // za Request i Response.

    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        Author author = new Author();

        author.setFirst_name(request.queryParams("first_name"));
        if(!request.queryParams("middle_name").trim().equals("")) {
            author.setMiddle_name(request.queryParams("middle_name"));
        }
        author.setLast_name(request.queryParams("last_name"));
        if(!request.queryParams("born").trim().equals("")) {
            author.setBorn(LocalDate.parse(request.queryParams("born")));
        }
        if(!request.queryParams("died").trim().equals("")) {
            author.setDied(LocalDate.parse(request.queryParams("died")));
        }
        try {
            InputStream is = request.raw().getPart("photo").getInputStream();
            //vraca nam nazad InputStream(sa slikom je uvek drugacije izgleda)
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Thumbnails.of(is).size(200,200).outputFormat("png").toOutputStream(os);
            author.setPhoto(os.toByteArray());
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        author.setBiography(request.queryParams("biography"));
        boolean isSaved = AuthorDAO.save(author);

        HashMap<String, Object> data = new HashMap<>();
        if(isSaved) {
            data.put("status", "success");
            data.put("message", "Author successfully saved!");
        } else {
            data.put("status", "error");
            data.put("message", "Error saving author.");
        }

        return new ModelAndView(data, "operation_status");
    }
}

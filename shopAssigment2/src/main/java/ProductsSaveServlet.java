import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/ProductsSaveServlet")
public class ProductsSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


        String label = req.getParameter("label");
        String price = req.getParameter("price");
        String onStock=req.getParameter("stock");

        Products p = new Products();

        p.setLabel(label);
        p.setPrice(Integer.parseInt(price));
        p.setStock(Integer.parseInt(onStock));

        int status = 0;
        try {

            status = ProductsDAO.save(p);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(status > 0){
            out.println("<p>Products saved succesfully</p>");
            req.getRequestDispatcher("index.html").include(req,resp);
        }
        else {
            out.println("<p>You havent add the Product!</p>");
        }
    }
}

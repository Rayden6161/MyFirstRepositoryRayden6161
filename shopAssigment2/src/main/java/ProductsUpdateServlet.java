import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class ProductsUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<h1>Update Product: </h1>");

            String sid = req.getParameter("product_id");
            Integer id = Integer.parseInt(sid);
           Products p = ProductsDAO.getProductById(id);
            out.print("<form action='ProductsUpdateServlet2' method='post'>");
            out.print("<table>");
            out.print("<tr><td></td><td><input type='hidden' name='product_id' value='" + p.getProduct_id() + "'/></td></tr>");
            out.print("<tr><td>Label:</td><td><input type='text' name='label' value='" + p.getLabel()+ "'/></td></tr>");
            out.print("<tr><td>Price:</td><td><input type='text' name='price' value='" + p.getPrice() + "'/></td></tr>");
            out.print("<tr><td>OnStock:</td><td><input type='text' name='onStock' value='"+ p.getOnStock()+"'/></td></tr> ");
            out.print("<tr><td colspan='2'><input type='submit' value='update and save'/></td></tr>");
            out.print("</table>");
            out.print("</form>");

            out.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch (NumberFormatException e){
            e.getMessage();
        }



    }
}



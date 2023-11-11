import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/ProductsListServlet")
public class ProductsListServlet extends HttpServlet {
    List<Products> list;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<a href='index.html'> Add new Products</a>");
        out.println("<h1> Products list:</h1>");
        try {
            list =ProductsDAO.getAllProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("<table border='1' width='60%'");
        out.println("<tr><th>product_id</th><th>label</th><th>price</th><th>stock</th></tr>");
        for(Products p:list) {
            out.println("<tr><td>"+p.getProduct_id() +"</td><td>"+ p.getLabel() +"</td><td>" + p.getPrice() +"</td><td>"+ p.getStock()+"</td><td><a href='ProductsUpdateServlet?product_id="+ p.getProduct_id()+"'>Update</a></td><td> " +
                    "<a href='ProductsDeleteServlet?product_id=" + p.getProduct_id() +"'>Delete</a></td></tr>");
        }
        out.println("</table>");
        out.close();
    }
    }
//zamenimo getlabel i getonstock

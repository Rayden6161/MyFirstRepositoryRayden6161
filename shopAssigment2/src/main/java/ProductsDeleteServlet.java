import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/ProductsDeleteServlet")
public class ProductsDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        try {
            String sid =req.getParameter("product_id");
            int id =Integer.parseInt(sid);
            ProductsDAO.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("ProductsListServlet");
    }
    }


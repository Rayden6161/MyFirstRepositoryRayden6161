import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/BuyersDeleteServlet")
public class BuyersDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
String sid =req.getParameter("buyer_id");
int id =Integer.parseInt(sid);
        try {
           int status= BuyersDAO.delete(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
resp.sendRedirect("BuyersListServlet");



    }
}

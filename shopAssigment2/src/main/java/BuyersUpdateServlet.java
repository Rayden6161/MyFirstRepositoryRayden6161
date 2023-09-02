import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/BuyersUpdateServlet")
public class BuyersUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException,NumberFormatException {


        try {

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<h1>Update buyer: </h1>");

            String sid = req.getParameter("buyer_id");
            Integer id = Integer.parseInt(sid);
            Buyers  b = BuyersDAO.getBuyersById(id);
            out.print("<form action='BuyersUpdateServlet2' method='post'>");
            out.print("<table>");
            out.print("<tr><td></td><td><input type='hidden' name='buyer_id' value='" + b.getBuyer_id() + "'/></td></tr>");
            out.print("<tr><td>Name:</td><td><input type='text' name='name' value='" + b.getName() + "'/></td></tr>");
            out.print("<tr><td>Balance:</td><td><input type='text' name='text' value='" + b.getBalance() + "'/></td></tr>");
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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/BuyersListServlet")
public class BuyersListServlet extends HttpServlet {
    List<Buyers> list;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException,NumberFormatException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<a href='index.html'> Add new buyers</a>");
        out.println("<h1> Buyers list:</h1>");
        try {
            list =BuyersDAO.getAllBuyers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("<table border='1' width='60%'>");
        out.println("<tr><th>buyer_id</th><th>name</th><th>balance</th></tr>");
        for(Buyers b:list) {
            out.println("<tr><td>"+b.getBuyer_id() +"</td><td>"+ b.getName() +"</td><td>" + b.getBalance()
                    +"</td><td><a href='BuyersUpdateServlet?buyer_id="+ b.getBuyer_id()+"'>Update</a></td><td> " +
                    "<a href='BuyersDeleteServlet?buyer_id=" + b.getBuyer_id() +"'>Delete</a></td></tr>");
        }
        out.println("</table>");
        out.close();
    }}

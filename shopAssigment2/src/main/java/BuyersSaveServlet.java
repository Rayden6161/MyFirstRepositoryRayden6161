import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/BuyersSaveServlet")
public class BuyersSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


String name = req.getParameter("name");
String balance = req.getParameter("balance");

Buyers b = new Buyers();

b.setName(name);
b.setBalance(Integer.parseInt(balance));

int status = BuyersDAO.save(b);

if(status > 0){
out.println("<p>Buyer saved succesfully</p>");
req.getRequestDispatcher("index.html").include(req,resp);
}
else {
out.println("<p>You havent add the buyer!</p>");
}
    }
}

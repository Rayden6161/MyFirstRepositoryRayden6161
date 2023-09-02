import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/BuyersUpdateServlet2")
public class BuyersUpdateServlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

   try {
       resp.setContentType("text/html");

       PrintWriter out = resp.getWriter();
String sid =req.getParameter("buyer_id");

Integer id =Integer.parseInt(sid);
String name = req.getParameter("name");
String balance =req.getParameter("balance");

Buyers b = new Buyers();
b.setBuyer_id(id);
b.setName(name);
b.setBalance(Integer.parseInt(balance));
        int status;
        try {
            status = BuyersDAO.update(b);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(status>0){
            resp.sendRedirect("BuyersListServlet");
        }else{
            out.println("<h1>Sorry! unable to update buyer</h1>");
        }

        out.close();}
   catch (NumberFormatException e ){
       e.printStackTrace();
   }
    }
    }


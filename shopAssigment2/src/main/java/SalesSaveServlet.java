import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
@WebServlet("/SalesSaveServlet")
public class SalesSaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String BuyerId = req.getParameter("buyer_id");
        String ProductId = req.getParameter("product_id");
        String productAmount=req.getParameter("product_amount");

        Sales s = new Sales();

        s.setBuyer_id(Integer.parseInt(BuyerId));
        s.setProduct_id(Integer.parseInt(ProductId));
        s.setProduct_amount(Integer.parseInt(productAmount));


        int status = 0;
        try {
            SalesDAO.getAllSales();
            status = SalesDAO.save(s);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(status > 0){
            out.println("<p>Sale saved succesfully</p>");
            req.getRequestDispatcher("index.html").include(req,resp);
        }
        else {
            out.println("<p>You havent add the Sale!</p>");
        }
    }
}

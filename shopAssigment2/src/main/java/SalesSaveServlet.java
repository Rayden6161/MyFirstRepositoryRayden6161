import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.BigDecimal;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet("/SalesSaveServlet")
public class SalesSaveServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        int BuyerId = Integer.parseInt(req.getParameter("buyer_id"));
        int ProductId = Integer.parseInt(req.getParameter("product_id"));
        int productAmount = Integer.parseInt(req.getParameter("product_amount"));


        if (BuyerId <= 0 || ProductId <= 0 || productAmount <= 0) {
            resp.getWriter().println("<p>Invalid input. Please enter positive integers.</p>");
            return;
        }


        Buyers buyer = null;
        Products product = null;
        try {
            product = ProductsDAO.getProductById(ProductId);
            buyer = BuyersDAO.getBuyersById(BuyerId);

            BigDecimal buyerBalance = BigDecimal.valueOf(buyer.getBalance());
            BigDecimal productPrice = BigDecimal.valueOf(product.getPrice());
            BigDecimal totalPrice = productPrice.multiply(BigDecimal.valueOf(productAmount));


            // Check if the buyer has enough balance
            if (buyerBalance.compareTo(totalPrice) < 0) {
                resp.getWriter().println("<p>Insufficient balance. Cannot complete the purchase.</p>");
                return;
            }


            // Check if the product has enough stock
            if (product.getStock() < productAmount) {

                resp.getWriter().println("<p>Insufficient stock. Cannot complete the purchase.</p>");

                return;
            }


            Sales s = new Sales();
            /*Products p = new Products();*/


            s.setBuyer_id(BuyerId);;
            s.setProduct_id(ProductId);
            s.setProduct_amount(productAmount);


            int status = 0;


            status = SalesDAO.save(s);


            if(status > 0){
                out.println("<p>Sale saved succesfully</p>");
                req.getRequestDispatcher("index.html").include(req,resp);
            }
            else {
                out.println("<p>You havent add the Sale!</p>");
            }
        }
         catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    /*    try {
            SalesDAO.updateStock();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/


    }
}


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
@WebServlet("/ProductsUpdateServlet2")
public class ProductsUpdateServlet2 extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html");

            PrintWriter out = resp.getWriter();
            String sid =req.getParameter("product_id");

            Integer id =Integer.parseInt(sid);
            String label = req.getParameter("label");
            String price =req.getParameter("price");
            String onStock=req.getParameter("onStock");

           Products p=new Products();
            p.setProduct_id(id);
            p.setLabel(label);
            p.setPrice(Integer.parseInt(price));
            p.setOnStock(Integer.parseInt(onStock));

            int status;
            try {
                status = ProductsDAO.update(p);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(status>0){
                resp.sendRedirect("ProductsListServlet");
            }else{
                out.println("<h1>Sorry! unable to update products</h1>");
            }

            out.close();}
        catch (NumberFormatException e ){
            e.printStackTrace();
        }
    }
}

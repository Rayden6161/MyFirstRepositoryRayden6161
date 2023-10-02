import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO {
public static Connection getConnection() throws ClassNotFoundException, SQLException {
    Connection conn = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver.");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "");

    }
    catch (SQLException e){
        e.getMessage();
    }
    return conn;
}


    public static List<Sales> getAllSales() throws SQLException {
        List<Sales>list =new ArrayList<>();
        try{
            Connection conn = ProductsDAO.getConnection();
            PreparedStatement ps = conn.prepareStatement("select buyers.name,buyers.balance,products.label,products.price, +\n" +
                    "      \" products.onStock from products JOIN sales ON products.product_id=sales.product_id JOIN buyers ON buyers.buyer_id=sales.buyer_id;\"");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              Sales s = new Sales();
                s.setBuyer_id(rs.getInt(1));
                s.setProduct_id(rs.getInt(2));
                s.setProduct_amount(rs.getInt(3));


            }conn.close();
        } catch (SQLException e) {
            e.getMessage();
        }

        return list;
    }



 /*   public static Sales getProductAndBuyerById(int buyer_id,int product_id) throws SQLException {
        Products p = new Products();
        Buyers b = new Buyers();
        try{
            Connection conn =ProductsDAO.getConnection();
            PreparedStatement ps= conn.prepareStatement("select buyers.name,buyers.balance,products.label,products.price," +
                    " products.onStock from products JOIN sales ON products.product_id=sales.product_id JOIN buyers ON buyers.buyer_id=sales.buyer_id;");
            ps.setInt(1,buyer_id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                p.setProduct_id(rs.getInt(1));
                p.setLabel(rs.getString(2));
                p.setOnStock(rs.getInt(3));
                p.setPrice(rs.getInt(4));

            }conn.close();

        } catch (SQLException e) {
            e.getMessage();
        }
*/
     /*   return null;
    }*/
    public static int save(Sales s) throws SQLException {
        int status=0;
        try {
            Connection conn =ProductsDAO.getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into sales(buyer_id,product_id,product_amount)values(?,?,?)");

            ps.setInt(1,s.getBuyer_id());
            ps.setInt(2,s.getProduct_id());
            ps.setInt(3,s.getProduct_amount());



            status=ps.executeUpdate();
            conn.close();
        }catch (SQLException e){
            e.getMessage();

        }
        return status;
    }
}


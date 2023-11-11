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


    public static List<Sales> getAllSales() throws SQLException, ClassNotFoundException {
        List<Sales>list =new ArrayList<>();
        try{
            Connection conn = SalesDAO.getConnection();
            PreparedStatement ps = conn.prepareStatement("select buyers.name,buyers.balance,products.label,products.price, +\n" +
                    "      \" products.stock from products JOIN sales ON products.?=sales.? JOIN buyers ON buyers.?=sales.?;\"");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
              Sales s = new Sales();
                s.setBuyer_id(rs.getInt(3));
                s.setBuyer_id(rs.getInt(4));
                s.setProduct_id(rs.getInt(1));
                s.setProduct_id(rs.getInt(2));

            }conn.close();
        } catch (SQLException e) {
            e.getMessage();
        }

        return list;
    }



    public static int update(Sales s) throws SQLException,NumberFormatException {
        int status= 0;
        try{
            Connection conn=BuyersDAO.getConnection();
            PreparedStatement ps =conn.prepareStatement("update sales set buyer_id=?,product_id=? ,product_amount=? where sales_id=?");

        Statement stmt=null ;
           ps.setString(1, String.valueOf(s.getBuyer_id()));
            ps.setString(2, String.valueOf(s.getProduct_id()));
            ps.setString(3, String.valueOf(s.getProduct_amount()));
            ps.setString(4, String.valueOf(s.getSales_id()));

      status= ps.executeUpdate();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }
 /*public static void addSales(int buyer, int product, int amount,int onStock) throws SQLException{


    Connection con;

    ResultSet rs;
    int diff;
    try {
            con = SalesDAO.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT onStock FROM products WHERE product_id =?");
        rs = ps.executeQuery();

        if(rs.next()){
          onStock=Integer.parseInt(rs.getString(onStock));
          amount=Integer.parseInt(rs.getString(amount));

            if(onStock >= amount){

                 diff = onStock-amount;
                try {
                    ps = con.prepareStatement("update products set onStock =? where product_id =?",product);
                    ps.setInt(1,diff);
                    ps.setInt(2,product);
                    ps.executeQuery();

                }catch (Exception e){

                }
                 ps = con.prepareStatement("INSERT INTO sales(buyer_id,product_id,product_amount) values(?,?,?)",buyer,product,amount);
                 ps.executeQuery();
            }else {
                System.err.println("There is no enough products on stock");
            }
        } else{
            System.err.println("Problem with first statement!");
        }

    } catch (Exception e){

    }


 }
*/

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
                p.setStock(rs.getInt(3));
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


    public static void updateStock() throws SQLException, ClassNotFoundException {
        Connection con = null;

        Statement st = null;
        try {
            con = SalesDAO.getConnection();
            st = con.createStatement();

            st.execute(" UPDATE products\n" +
                    "            JOIN sales on sales.product_id = products.products_id\n" +
                    "            SET products.stock = (products.stock - sales.product_amount)");

        } catch (Exception e) {
            e.getMessage();
        }
    }
    }








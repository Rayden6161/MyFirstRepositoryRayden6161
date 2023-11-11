import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO {
    public static Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
    public static int save(Products p) throws SQLException {
        int status=0;
        try {
            Connection conn =ProductsDAO.getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into products(label,price,stock)values(?,?,?)");

                ps.setString(1,p.getLabel());
                ps.setInt(2,p.getPrice());
                ps.setInt(3,p.getStock());


                status=ps.executeUpdate();
                conn.close();
        }catch (SQLException e){
            e.getMessage();

        }
        return status;
    }
    public static int delete(int id) throws SQLException {
int status=0;

        try{    Connection conn =ProductsDAO.getConnection();
            PreparedStatement ps = conn.prepareStatement("delete from products where product_id=?");
            ps.setInt(1,id);
            status=ps.executeUpdate();
            conn.close();

        }catch (SQLException e){
            e.getMessage();
        }
        return status;
    }
    public static int update(Products p) throws SQLException {
        int status= 0;
        try{Connection conn = ProductsDAO.getConnection();
            PreparedStatement ps = conn.prepareStatement("update products set label=?,price=?,stock=? where product_id=?");
            ps.setString(1,p.getLabel());
            ps.setInt(2,p.getPrice());
            ps.setInt(3,p.getStock());
            ps.setInt(4,p.getProduct_id());
            status= ps.executeUpdate();
            conn.close();
        }catch (SQLException e){
            e.getMessage();
        }
        return status;
    }
    public static Products getProductById(int product_id) throws SQLException {
        Products p = new Products();
        try{
            Connection conn =ProductsDAO.getConnection();
            PreparedStatement ps= conn.prepareStatement("select * from products where product_id=?");
            ps.setInt(1,product_id);
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
        return p;
    }

    public static List<Products> getAllProducts() throws SQLException {
        List<Products>list =new ArrayList<>();
        try{
            Connection conn = ProductsDAO.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from products");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Products p = new Products();
                p.setProduct_id(rs.getInt(1));
                p.setLabel(rs.getString(2));
                p.setStock(rs.getInt(3));
                p.setPrice(rs.getInt(4));
                list.add(p);


            }conn.close();
        } catch (SQLException e) {
            e.getMessage();
        }


        return list;
    }
}

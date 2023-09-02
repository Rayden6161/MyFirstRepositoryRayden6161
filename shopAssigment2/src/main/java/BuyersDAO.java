import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuyersDAO {
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
public static int save(Buyers b){
int status= 0;
try {
    Connection conn = BuyersDAO.getConnection();
    PreparedStatement ps = conn.prepareStatement("insert into buyers (name,balance) values(?,?)");

ps.setString(1,b.getName());
ps.setString(2, String.valueOf(b.getBalance()));
status=ps.executeUpdate();
conn.close();
}
 catch (SQLException e) {
    throw new RuntimeException(e);
}
    return status;
}
public static int update(Buyers b) throws SQLException,NumberFormatException {
int status= 0;
try{
    Connection conn=BuyersDAO.getConnection();
    PreparedStatement ps =conn.prepareStatement("update buyers set name=?,balance=? where buyer_id=?");
    ps.setString(1,b.getName());
    ps.setString(2, String.valueOf(b.getBalance()));
    ps.setString(3, String.valueOf(b.getBuyer_id()));

status= ps.executeUpdate();
conn.close();

}catch (Exception e){
    e.printStackTrace();
}
    return status;
}
public static int delete(int id) throws SQLException {
        int status=0;
        try {
            Connection conn = BuyersDAO.getConnection();
            PreparedStatement ps = conn.prepareStatement("delete from buyers where buyer_id=?");
       ps.setInt(1,id);
status=ps.executeUpdate();
conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return status;
}
public static Buyers getBuyersById(int id) throws SQLException {
Buyers b = new Buyers();
try{
    Connection conn = BuyersDAO.getConnection();
PreparedStatement ps = conn.prepareStatement("select * from buyers where buyer_id=?");
ps.setInt(1,id);
    ResultSet rs = ps.executeQuery();
if(rs.next()){
b.setBuyer_id(rs.getInt(1));
b.setName(rs.getString(2));
b.setBalance(rs.getInt(3));

}conn.close();
} catch (SQLException e) {
    throw new RuntimeException(e);
}
    return b;
}
public static List<Buyers> getAllBuyers() throws SQLException {
List<Buyers> list= new ArrayList<Buyers>();
try{
    Connection conn = BuyersDAO.getConnection();
PreparedStatement ps = conn.prepareStatement("select * from buyers;");
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
Buyers b = new Buyers();
b.setBuyer_id(rs.getInt(1));
        b.setName(rs.getString(2));
        b.setBalance(rs.getInt(3));
list.add(b);
    }conn.close();
} catch (SQLException e) {
    throw new RuntimeException(e);
}

    return list;
}

}

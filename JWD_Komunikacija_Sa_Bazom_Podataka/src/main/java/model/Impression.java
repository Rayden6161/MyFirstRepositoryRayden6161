package model;

import java.sql.*;

public class Impression {
    private int id ;
    private String name;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static String all() throws ClassNotFoundException { //metoda za citanje svih utisaka
        StringBuilder  sb = new StringBuilder();
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test1","root",""))
        {
            Statement st =conn.createStatement();
            st.executeQuery("select name,text from impressions");
            ResultSet rs = st.getResultSet();

            while (rs.next()){
                //dodjemo do utisaka i smestamo ih u resultset  pomocu stringbuildera
                sb.append(rs.getString("name"));
                sb.append(":\n");
                sb.append(rs.getString("text"));
                sb.append("\n\n");
            }
        } catch (SQLException e) {
            sb.append(e.getMessage());
        }
        return sb.toString(); //na kraju prikazemo te Stringove.
    }


    public void  insert () throws ClassNotFoundException {
        //ova metoda nije statik jer zelimo da bude moguce da je pozovemo nad instancom ove impresiion klase.
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test1","root","")) {
      if(name != null && !(name.isEmpty()) && text !=null && !(text.isEmpty()) ){
          Statement st = conn.createStatement();
          st.execute("insert into impressions(name ,text) values('"+name  + "','" +text + "')");

      }

        } catch (SQLException e) {
            System.out.println("Error in database connection: \n " +e.getMessage());
        }
    }
}

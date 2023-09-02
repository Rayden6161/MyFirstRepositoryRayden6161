

import db.Worker;
import main.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class Main{
    public static void main(String[] args) throws SQLException {




        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;
        try {
            Worker worker =new Worker("Marko",22,"Lovcenac 13 Jula",333);
            tx = session.beginTransaction();

            session.get(Worker.class,1);
            worker.setName("Dragan");
            session.update(worker);

            //session.persist(worker);

            /*session.get(Worker.class,3);
            session.delete(worker);*/

            for (Worker worker1 : (List<Worker>) session.createQuery("select * from workerstable;", Worker.class)) {
                System.out.println(worker1);
            }




            tx.commit();
        }
        catch(HibernateException e) {
            if(tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        }
        finally {
            main.HibernateUtil.close();
        }


    }
}
package db;


import org.hibernate.Session;

import java.sql.*;
import java.util.List;

public class Db {
    Session session = main.HibernateUtil.createSessionFactory().openSession();






    public void insertWorker(Worker worker) throws SQLException {

        session.persist(worker);
    }

    public void deleteWorker(Worker worker,int id) throws SQLException {
        session.get(Worker.class,id);
        session.delete(worker);

    }

    public void updateWorker(Worker worker) throws SQLException {
        session.get(Worker.class,worker.getId());
        session.update(worker);


    }

    public List<Worker> getAllWorkers() throws SQLException {


        return (List<Worker>) session.createQuery("select * from workerstable;" ,Worker.class);
    }

    public Worker getWorkerById() throws SQLException {
        Worker worker = null;
        session.get(Worker.class,worker.getId());





        return worker;
    }


}

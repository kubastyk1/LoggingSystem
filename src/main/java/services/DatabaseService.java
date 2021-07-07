package services;

import config.HibernateUtil;
import model.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DatabaseService<T> {

    private Session session;

    public DatabaseService() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public synchronized void save(T record) {
        Transaction transaction = session.beginTransaction();
        session.save(record);
        transaction.commit();
    }

    public List<T> getAllRecords(String tableName) {
        return (List<T>) session.createQuery("from " + tableName, Event.class).list();
    }

    public void printRecords(String tableName) {
        getAllRecords(tableName).forEach(System.out::println);
    }

    public void closeSession() {
        HibernateUtil.getSessionFactory().close();
    }
}

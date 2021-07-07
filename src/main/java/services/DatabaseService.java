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

    public void save(T record) {
        Transaction transaction = session.beginTransaction();
        session.save(record);
        transaction.commit();
    }

    public void printRecords(String tableName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Event> students = session.createQuery("from " + tableName, Event.class).list();
            students.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

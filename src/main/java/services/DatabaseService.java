package services;

import config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}

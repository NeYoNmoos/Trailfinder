package at.fhv.hike.hibernate.broker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public abstract class BrokerBase<T> {

    private SessionFactory sessionFactory;
    private Class<T> entityClass;

    public BrokerBase(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    public void insert(T value) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(value);
            tx.commit();
        }
    }

    public void update(T value) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(value);
            tx.commit();
        }
    }

    public void delete(T value) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(value);
            tx.commit();
        }
    }

    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from " + entityClass.getName(), entityClass).list();
        }
    }

    public T getById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityClass, id);
        }
    }
}
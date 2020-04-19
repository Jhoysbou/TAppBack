package com.tapp.api.v1.dao;

import com.tapp.api.v1.models.User;
import com.tapp.api.v1.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {
    @Override
    public Optional<User> get(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        return Optional.of(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = (List<User>) HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("from User", User.class)
                .list();
        return users;
    }

    @Override
    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();

    }

    @Override
    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteById(long id) {
        String hql = "delete User where id=:id";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query q = session.createQuery(hql).setParameter("id", id);
        q.executeUpdate();
        tx1.commit();
        session.close();

    }


}

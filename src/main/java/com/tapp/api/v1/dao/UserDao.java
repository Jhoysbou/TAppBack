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
        session.close();
        return Optional.of(user);
    }

    @Override
    public List<User> getAll() {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();

        List<User> users = (List<User>) session
                .createQuery("from User", User.class)
                .list();
        session.close();
        return users;
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

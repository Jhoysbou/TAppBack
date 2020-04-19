package com.tapp.api.v1.dao;

import com.tapp.api.v1.models.Test;
import com.tapp.api.v1.models.UsersTests;
import com.tapp.api.v1.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class UsersTestsDao implements Dao<UsersTests> {
    @Override
    public Optional<UsersTests> get(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        UsersTests usersTests = session.get(UsersTests.class, id);
        session.close();
        return Optional.of(usersTests);
    }

    @Override
    public List<UsersTests> getAll() {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("from UsersTests ", UsersTests.class)
                .list();
    }

    @Override
    public void deleteById(long id) {
        String hql = "delete UsersTests where id=:id";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query q = session.createQuery(hql).setParameter("id", id);
        q.executeUpdate();
        tx1.commit();
        session.close();
    }
}

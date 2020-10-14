package com.tapp.api.v1.dao;

import com.tapp.api.v1.models.Test;
import com.tapp.api.v1.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class TestDao implements Dao<Test> {
    @Override
    public Optional<Test> get(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Test test = session.get(Test.class, id);
        session.close();
        return Optional.ofNullable(test);
    }

    @Override
    public List<Test> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Test> tests = session
                .createQuery("from Test", Test.class)
                .setCacheable(true)
                .list();

        session.close();
        return tests;
    }

    @Override
    public void deleteById(long id) {
        String hql = "delete Test where id=:id";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query q = session.createQuery(hql).setParameter("id", id);
        q.setCacheable(true);
        q.executeUpdate();
        tx1.commit();
        session.close();
    }


}

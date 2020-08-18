package com.tapp.api.v1.dao;

import com.tapp.api.v1.models.HistoryEvent;
import com.tapp.api.v1.models.Test;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class HistoryEventDao implements Dao<HistoryEvent> {
    @Override
    public Optional<HistoryEvent> get(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        HistoryEvent historyEvent = session.get(HistoryEvent.class, id);
        session.close();
        return Optional.ofNullable(historyEvent);
    }

    @Override
    public List<HistoryEvent> getAll() {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();

        List<HistoryEvent> history = session
                .createQuery("from HistoryEvent", HistoryEvent.class)
                .setCacheable(true)
                .list();

        session.close();
        return history;
    }

    @Override
    public void deleteById(long id) {
        String hql = "delete HistoryEvent where id=:id";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query q = session.createQuery(hql).setParameter("id", id);
        q.setCacheable(true);
        q.executeUpdate();
        tx1.commit();
        session.close();
    }

    public void deleteByUser(final User user) {
        String hql = "delete HistoryEvent where user=:user";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql).setParameter("user", user);
        q.setCacheable(true);
        q.executeUpdate();
        tx.commit();
        session.close();
    }

    public List<HistoryEvent> getByUserTestHistory(final User user, final Test test) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();

        List<HistoryEvent> history = session
                .createQuery("from HistoryEvent where user=:user and test=:test", HistoryEvent.class)
                .setCacheable(true)
                .setParameter("user", user)
                .setParameter("test", test)
                .list();

        session.close();

        return history;
    }
}

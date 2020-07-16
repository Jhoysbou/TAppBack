package com.tapp.api.v1.dao;

import com.tapp.api.v1.models.HistoryEvent;
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
        return Optional.of(historyEvent);
    }

    @Override
    public List<HistoryEvent> getAll() {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("from HistoryEvent", HistoryEvent.class)
                .list();
    }

    @Override
    public void deleteById(long id) {
        String hql = "delete HistoryEvent where id=:id";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query q = session.createQuery(hql).setParameter("id", id);
        q.executeUpdate();
        tx1.commit();
        session.close();
    }
}
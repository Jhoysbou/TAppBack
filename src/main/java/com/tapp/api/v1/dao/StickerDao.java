package com.tapp.api.v1.dao;

import com.tapp.api.v1.models.Sticker;
import com.tapp.api.v1.models.User;
import com.tapp.api.v1.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class StickerDao implements Dao<Sticker> {
    @Override
    public Optional<Sticker> get(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Sticker sticker = session.get(Sticker.class, id);
        session.close();
        return Optional.of(sticker);
    }

    @Override
    public List<Sticker> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Sticker> stickers = session
                .createQuery("from Sticker ", Sticker.class)
                .list();

        session.close();
        return stickers;
    }

    @Override
    public void deleteById(long id) {
        String hql = "delete Sticker where id=:id";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query q = session.createQuery(hql).setParameter("id", id);
        q.executeUpdate();
        tx1.commit();
        session.close();
    }
}

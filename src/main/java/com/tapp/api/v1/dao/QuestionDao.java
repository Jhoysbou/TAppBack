package com.tapp.api.v1.dao;

import com.tapp.api.v1.models.Question;
import com.tapp.api.v1.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class QuestionDao implements Dao<Question> {
    @Override
    public Optional<Question> get(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Question question = session.get(Question.class, id);
        session.close();
        return Optional.of(question);
    }

    @Override
    public List<Question> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Question> questions = session
                .createQuery("from Question ", Question.class)
                .list();

        session.close();
        return questions;
    }

    @Override
    public void deleteById(long id) {
        String hql = "delete Question where id=:id";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query q = session.createQuery(hql).setParameter("id", id);
        q.executeUpdate();
        tx1.commit();
        session.close();
    }
}

package com.tapp.api.v1.utils;

import com.tapp.api.v1.models.Answer;
import com.tapp.api.v1.models.Question;
import com.tapp.api.v1.models.Test;
import com.tapp.api.v1.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Test.class);
            configuration.addAnnotatedClass(Question.class);
            configuration.addAnnotatedClass(Answer.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Exception e) {
            System.out.print("Ecxeption in HibernateSessionFactoryUtil: " + e);
        }
        return sessionFactory;
    }
}

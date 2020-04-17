package com.tapp.api.utils;

import com.tapp.api.models.Answer;
import com.tapp.api.models.Question;
import com.tapp.api.models.Test;
import com.tapp.api.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Test.class);
                configuration.addAnnotatedClass(Question.class);
                configuration.addAnnotatedClass(Answer.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Ecxeption in HibernateSessionFactoryUtil: " + e);
            }
        }
        return sessionFactory;
    }
}

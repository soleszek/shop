package com.sylwesteroleszek.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;

public class HibernateUtils {
    private final static HibernateUtils instance = new HibernateUtils();
    private SessionFactory sessionFactory;
    private Connection connection;

    private HibernateUtils(){
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("Hibernate.xml")
                .build();
        Metadata metadata = new MetadataSources(standardRegistry)
                .getMetadataBuilder()
                .build();
        sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();
    }

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static HibernateUtils getInstance(){
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}

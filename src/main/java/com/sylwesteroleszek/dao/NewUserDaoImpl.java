package com.sylwesteroleszek.dao;

import com.sylwesteroleszek.entity.NewUser;
import com.sylwesteroleszek.utils.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.NoResultException;

public class NewUserDaoImpl implements NewUserDao {
    @Override
    public void saveOrUpdate(NewUser newUser) {
        Session session = HibernateUtils.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(newUser);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public NewUser findBy(String login) {
        try {
            Session session = HibernateUtils.getInstance().getSessionFactory().getCurrentSession();
            session.beginTransaction();
            NewUser newUser = (NewUser)session.createQuery("from NewUser where login=:login")
                    .setParameter("login", login).getSingleResult();
            session.getTransaction().commit();
            session.clear();

            return newUser;
        } catch (NoResultException e){
            return null;
        }
    }
}

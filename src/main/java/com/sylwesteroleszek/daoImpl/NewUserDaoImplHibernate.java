package com.sylwesteroleszek.daoImpl;

import com.sylwesteroleszek.dao.NewUserDao;
import com.sylwesteroleszek.entity.NewUser;
import com.sylwesteroleszek.utils.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

public class NewUserDaoImplHibernate implements NewUserDao {

    public void saveUser(NewUser newUser) {
        Session session = HibernateUtils.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(newUser);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<NewUser> readUsers() {
        return null;
    }

    @Override
    public void updateUser(NewUser newUser) {

    }

    /*@Override
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
    }*/

    /*@Override
    public List<NewUser> findAll() {
        return null;
    }

    @Override
    public void delete(String username) {

    }

    @Override
    public void delete(NewUser newUser) {

    }*/
}

//package com.cherednik.dao;
//
//import com.cherednik.model.User;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.query.Query;
//
//import java.util.List;
//
//public class UserDao {
//    private SessionFactory sessionFactory;
//
//    public UserDao() {
//        sessionFactory = new Configuration().configure().buildSessionFactory();
//    }
//
//    public User addUser(User user) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.save(user);
//            transaction.commit();
//            return user;
//        }
//    }
//
//    public int updateUser(User user) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            String sql = String.format("UPDATE User SET name ='%s', age = '%d' WHERE id = :id", user.getName(), user.getAge());
//            Query query = session.createQuery(sql).setParameter("id", user.getId());
//            int count = query.executeUpdate();
//            transaction.commit();
//            return count;
//        }
//    }
//
//    public List<User> getUser(int id) {
//        try (Session session = sessionFactory.openSession()) {
//            return session
//                    .createQuery("FROM User WHERE id = :id ", User.class)
//                    .setParameter("id", id)
//                    .list();
//        }
//    }
//
//    public List<User> getUsers() {
//        try (Session session = sessionFactory.openSession()) {
//            return session.createQuery("FROM User", User.class).list();
//        }
//    }
//
//    public int deleteUser(User user) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            String hql = String.format("DELETE FROM User WHERE id = :id ", User.class);
//            Query query = session.createQuery(hql).setParameter("id", user.getId());
//            int count = query.executeUpdate();
//            transaction.commit();
//            return count;
//        }
//    }
//
//    public int deleteAllUsers() {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            String hql = String.format("DELETE FROM %s", User.class.getSimpleName());
//            Query query = session.createQuery(hql);
//            int count = query.executeUpdate();
//            transaction.commit();
//            return count;
//        }
//    }

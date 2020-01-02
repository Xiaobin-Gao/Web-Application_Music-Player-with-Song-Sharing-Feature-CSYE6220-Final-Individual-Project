/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import xiaobin.gao.ap.model.Message;
import xiaobin.gao.ap.model.Song;
import xiaobin.gao.ap.model.User;

/**
 *
 * @author gao.xiaob
 */
public class MessageDao {

    public static void register(Message m) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.saveOrUpdate(m);
            t.commit();
            t = null;
            System.out.println("MessageDao - register() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("MessageDao - register() failed: " + e);
        } finally {
            session.close();
        }
    }

    public static Message fetchMessageByFromUserId(int fromUserId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Message m = null;
        Transaction t = null;
        try {
            t = session.beginTransaction();
            Query q = session.createQuery("FROM Message WHERE fromUserId=:fromUserId");
            q.setInteger("fromUserId", fromUserId);
            if (q.uniqueResult() != null) {
                m = (Message) q.uniqueResult();
            }
            t.commit();
            t = null;
            System.out.println("MessageDao - fetchMessageByFromUserId() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("MessageDao - fetchMessageByFromUserId() failed: " + e);
        } finally {
            session.close();
        }
        return m;
    }

    public static void deleteMessageById(int messId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Message m = null;
        Transaction t = null;
        try {
            t = session.beginTransaction();
            Query q = session.createQuery("DELETE FROM Message WHERE id=:messId");
            q.setInteger("messId", messId);
            q.executeUpdate();
            t.commit();
            t = null;
            System.out.println("MessageDao - deleteMessageById() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("MessageDao - deleteMessageById() failed: " + e);
        } finally {
            session.close();
        }
    }
}

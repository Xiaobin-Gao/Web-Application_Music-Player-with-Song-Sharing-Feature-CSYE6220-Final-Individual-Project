/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.service;

import java.util.List;
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
public class UserDao {

    public static int register(User u) {
        int i = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            i = (Integer) session.save(u);
            t.commit();
            t = null;
            System.out.println("UserDao - register() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("UserDao - register() failed: " + e);
        } finally {
            session.close();
        }
        return i;
    }

    public static User fetchUserById(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = null;
        Transaction t = null;
        try {
            t = session.beginTransaction();
            user = session.get(User.class, userId);
            t.commit();
            t = null;
            System.out.println("UserDao - fetchUserById() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("UserDao - fetchUserById() failed: " + e);
        } finally {
            session.close();
        }
        return user;
    }

    public static User fetehUserByNV(String attrName, String attrValue) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = null;
        Transaction t = null;
        try {
            t = session.beginTransaction();
            Query q = session.createQuery("FROM User WHERE " + attrName + "=:attrValue");
            q.setString("attrValue", attrValue);
            if (q.uniqueResult() != null) {
                user = (User) q.uniqueResult();
            }
            t.commit();
            t = null;
            System.out.println("UserDao - fetehUserByNV() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("UserDao - fetehUserByNV() failed: " + e);
        } finally {
            session.close();
        }
        return user;
    }

    public static User fetehUserByEP(String uEmail, String uPwd) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = null;
        Transaction t = null;
        try {
            t = session.beginTransaction();
            Query q = session.createQuery("FROM User WHERE email=:email and password=:pwd");
            q.setString("email", uEmail);
            q.setString("pwd", uPwd);
            if (q.uniqueResult() != null) {
                user = (User) q.uniqueResult();
            }
            t.commit();
            t = null;
            System.out.println("UserDao - fetehUserByEP() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("UserDao - fetehUserByEP() failed: " + e);
        } finally {
            session.close();
        }
        return user;
    }

    public static List<Song> fetchUserPlaylist(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Song> playlist = null;
        Transaction t = null;
        try {
            t = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                playlist = user.getPlaylist();

                //
                System.out.println(playlist.size());
            }
            t.commit();
            t = null;
            System.out.println("UserDao - fetchUserPlaylist() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("UserDao - fetchUserPlaylist() failed: " + e);
        } finally {
            session.close();
        }
        return playlist;
    }

    public static List<Message> fetchUserMessages(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Message> messages = null;
        Transaction t = null;
        try {
            t = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                messages = user.getMessages();

                //
                System.out.println(messages.size());
            }
            t.commit();
            t = null;
            System.out.println("UserDao - fetchUserMessages() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("UserDao - fetchUserMessages() failed: " + e);
        } finally {
            session.close();
        }
        return messages;
    }

    public static List<User> fetchUserFriends(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> friends = null;
        Transaction t = null;
        try {
            t = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                friends = user.getFriends();
                //
                System.out.println(friends.size());
            }
            t.commit();
            t = null;
            System.out.println("UserDao - fetchUserFriends() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("UserDao - fetchUserFriends() failed: " + e);
        } finally {
            session.close();
        }
        return friends;
    }

    public static void updateUserPlaylist(int userId, Song song, String op) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                List<Song> playlist = user.getPlaylist();
                if (op.equals("ADD")) {
                    playlist.add(song);
                }
                if (op.equals("DELETE")) {
                    playlist.remove(session.get(Song.class, song.getId()));
                }
                user.setPlaylist(playlist);
                session.update(user);
            }
            t.commit();
            t = null;
            System.out.println("UserDao - updateUserPlaylist() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("UserDao - updateUserPlaylist() failed: " + e);
        } finally {
            session.close();
        }
    }

    public static void updateUserFriends(int userId, User newF, String op) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                List<User> friends = user.getFriends();
                if (op.equals("ADD")) {
                    friends.add(newF);
                }
                if (op.equals("DELETE")) {
                    friends.remove(session.get(User.class, newF.getId()));
                }
                user.setFriends(friends);
                session.update(user);
            }
            t.commit();
            t = null;
            System.out.println("UserDao - updateUserFriends() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("UserDao - updateUserFriends() failed: " + e);
        } finally {
            session.close();
        }
    }

    public static void updateUserMessages(int userId, Message m, String op) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                List<Message> messages = user.getMessages();
                if (op.equals("ADD")) {
                    messages.add(m);
                }
                if (op.equals("DELETE")) {
                    messages.remove(session.get(Message.class, m.getId()));
                }
                user.setMessages(messages);
                session.update(user);
            }
            t.commit();
            t = null;
            System.out.println("UserDao - updateUserMessages() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("UserDao - updateUserMessages() failed: " + e);
        } finally {
            session.close();
        }
    }

    public static void updateUserPrime(int userId, boolean b) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                user.setPrime(b);
            }
            t.commit();
            t = null;
            System.out.println("UserDao - updateUserPrimes() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("UserDao - updateUserPrime() failed: " + e);
        } finally {
            session.close();
        }
    }
    
        public static void updateUserProfileImg(int userId, String fileName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                user.setProfileImg(fileName);
            }
            t.commit();
            t = null;
            System.out.println("UserDao - updateUserProfileImg() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("UserDao - updateUserProfileImg() failed: " + e);
        } finally {
            session.close();
        }
    }
}

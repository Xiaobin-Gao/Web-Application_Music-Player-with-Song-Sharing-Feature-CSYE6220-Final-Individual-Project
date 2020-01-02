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
import xiaobin.gao.ap.model.Song;

/**
 *
 * @author gao.xiaob
 */
public class SongDao {

    public static void register(Song s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.saveOrUpdate(s);
            t.commit();
            t = null;
            System.out.println("SongDao - register() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("SongDao - register() failed: " + e);
        } finally {
            session.close();
        }
    }

    public static Song fetchSongById(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Song song = null;
        Transaction t = null;
        try {
            t = session.beginTransaction();
            song = session.get(Song.class, id);
            t.commit();
            t = null;
            System.out.println("SongDao - fetchSongById() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("SongDao - fetchSongById() failed: " + e);
        } finally {
            session.close();
        }
        return song;
    }

    public static List<String> fetchSongArtists(String songId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<String> artists = null;
        Transaction t = null;
        try {
            t = session.beginTransaction();
            Song song = session.get(Song.class, songId);
            if (song != null) {
                artists = song.getArtists();

                // artists will be loaded only when being used at least once in the same session
                System.out.println(artists.size());
            }
            t.commit();
            t = null;
            System.out.println("SongDao - fetchSongArtists() succeeded.");
        } catch (HibernateException e) {
            if (t != null) {
                t.rollback();
            }
            System.out.println("SongDao - fetchSongArtists() failed: " + e);
        } finally {
            session.close();
        }
        return artists;
    }
}

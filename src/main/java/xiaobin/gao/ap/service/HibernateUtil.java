/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.service;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author gao.xiaob
 */
public class HibernateUtil {

    private static SessionFactory sf = null;

    private static void buildSessionFactory() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("airplayerdb.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        sf = meta.getSessionFactoryBuilder().build();
    }
    
    public static SessionFactory getSessionFactory() {
        if (sf == null) {
            buildSessionFactory();
        }
        return sf;
    }
}

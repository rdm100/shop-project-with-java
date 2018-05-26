package db;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import models.Customer;
import models.Order;
import models.Product;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBOrder {

    private static Session session;


    public static List<Product> findProductsInOrder(Order order) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Product> foundProducts = null;
        try {
            Criteria cr = session.createCriteria(Product.class);
            cr.createAlias("orders", "order");
            cr.add(Restrictions.eq("order.id", order.getId()));
            foundProducts = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return foundProducts;
    }
}
//
//
//    public static List<Team> findTeamsInMatch(Match match){
//        session = HibernateUtil.getSessionFactory().openSession();
//        List<Team> foundTeams = null;
//        try{
//            Criteria cr = session.createCriteria(Team.class);
//            cr.createAlias("matches", "match");
//            cr.add(Restrictions.eq("match.id", match.getId()));
//            foundTeams = cr.list();
//        }catch (HibernateException e){
//            e.printStackTrace();
//        }finally {
//            session.close();
//        } return foundTeams;
//    }
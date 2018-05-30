package db;

import models.Basket;
import models.Order;
import models.Product;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBBasket {

    private static Session session;


    public static List<Product> AllProductsInABasket(Basket basket) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Product> products = null;
        try {
            Criteria cr = session.createCriteria(Product.class);
            cr.createAlias("baskets", "basket");
            cr.add(Restrictions.eq("basket.id", basket.getId()));
            products = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }
}
//
//    public static List<Team> findTeamsInCompetition(Competition competition){
//        session = HibernateUtil.getSessionFactory().openSession();
//        List<Team> foundTeams = null;
//        try{
//            Criteria cr = session.createCriteria(Team.class, "team");
//            cr.createAlias("competitions", "competition");
//            cr.add(Restrictions.eq("competition.id", competition.getId()));
//
//            foundTeams = cr.list();
//        }catch (HibernateException e){
//            e.printStackTrace();
//        }finally {
//            session.close();
//        } return foundTeams;
//    }


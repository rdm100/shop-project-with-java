package db;

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

    public static List<Product> AllProductsBelongingToAOrder(Order order) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Product> products = null;
        try {
            Criteria cr = session.createCriteria(Product.class);
            cr.add(Restrictions.eq("order", order));
            products = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }
}

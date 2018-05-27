package db;

import models.Product;
import models.Stock;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import java.util.List;


    public class DBStock {

        private static Session session;

        public static List<Product> AllProductsBelongingToStock(Stock stock) {
            session = HibernateUtil.getSessionFactory().openSession();
            List<Product> products = null;
            try {
                Criteria cr = session.createCriteria(Product.class);
                cr.add(Restrictions.eq("stock", stock));
                products = cr.list();
            } catch (HibernateException e) {
                e.printStackTrace();
            } finally {
                session.close();
            }
            return products;
        }


    }


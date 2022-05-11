package hibernate;

import hibernate.entity.Customer;
import hibernate.withCfg.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ObtainEntityExample {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            // Obtain an entity using get() method
            Customer customer1 = session.get(Customer.class, 1L);
            if (customer1 != null) {
                System.out.println(customer1.getName());
            }

            // Obtain an entity using load() method
            Customer customer2 = session.load(Customer.class, 2L);
            System.out.println(customer2.getName());

            // Obtain an entity using byId() method
            Customer customer3 = session.byId(Customer.class).getReference(3L);
            System.out.println(customer3.getName());

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        HibernateUtil.shutdown();
    }
}

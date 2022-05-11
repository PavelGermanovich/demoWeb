package hibernate;

import hibernate.entity.Customer;
import hibernate.withCfg.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RemoveEntityEx {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            //Remove a Persistent object
            Customer customer1 = session.get(Customer.class, 3l);
            if (customer1 != null) {
                session.remove(customer1);
                System.out.println("Customer 3rd removed");
            }

            //Remove transient object
            Customer customer2 = new Customer();
            customer2.setId(4);
            session.remove(customer2);
            System.out.println("Customer 4 was removed");
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

package hibernate;

import hibernate.entity.Customer;
import hibernate.withCfg.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteEntityExample {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            //Delete a persistent object
            Customer customer = session.get(Customer.class, 1L);
            if (customer != null) {
                session.delete(customer);
                System.out.println("Customer one removed");
            }

            //Delete transient object
            Customer customer2 = new Customer();
            customer2.setId(2);
            session.delete(customer2);
            System.out.println("Customer 2 deleted");
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        HibernateUtil.shutdown();
    }
}

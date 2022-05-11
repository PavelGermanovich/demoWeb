package hibernate;

import hibernate.entity.Customer;
import hibernate.withCfg.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SaveEntityDemo {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();
            Customer customer4 = new Customer();
            customer4.setId(8);
            customer4.setName("Sixth");
            //Save method return id of saved object or exception if object with such id already exists in DB
            System.out.println(session.save(customer4));
            transaction.commit();

            transaction.begin();
            Customer customer5 = new Customer();
            customer5.setId(7);
            customer5.setName("Seven");
            //Saving object using persist method returns void or Exception, if object with such id exists
            session.persist(customer5);
            transaction.commit();


            transaction.begin();
            Customer customer = new Customer();
            customer.setId(3l);
            customer.setName("sssss");
            session.saveOrUpdate(customer);
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
    }
}

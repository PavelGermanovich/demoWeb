package hibernate.withoutCfg;

import org.hibernate.Session;

public class Demo {
    public static void main(String[] args) {
        Session session = HibernateUtilWithoutCfg.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "Select version()";
        String result = (String) session.createNativeQuery(sql).getSingleResult();
        System.out.println(result);

        session.getTransaction().commit();
        session.close();
        HibernateUtilWithoutCfg.shutdown();
    }
}

package hibernate.withCfg;

import org.hibernate.Session;

public class Demo {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String sql = "select version()";
        String result = (String) session.createNativeQuery(sql).getSingleResult();
        System.out.println(result);

        session.getTransaction().commit();
        session.close();

        HibernateUtil.shutdown();
    }
}

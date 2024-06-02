package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import Util.HibernateUtil;
import Model.Admin;

public class AdminDAO {

    public static boolean checkLogin(String username, String password) {
        boolean isValid = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Admin WHERE username = :username";
            Admin admin = session.createQuery(hql, Admin.class)
                    .setParameter("username", username)
                    .uniqueResult();
            if (admin != null && BCrypt.checkpw(password, admin.getPassword())) {
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }

    public void saveAdmin(Admin admin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Băm mật khẩu trước khi lưu
            String hashedPassword = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
            admin.setPassword(hashedPassword);
            session.save(admin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

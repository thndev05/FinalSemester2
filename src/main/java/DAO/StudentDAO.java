package DAO;

import Model.Score;
import Model.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Util.HibernateUtil;

import java.util.List;

public class StudentDAO implements DAOInterface<Student> {

    @Override
    public boolean insert(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Xóa điểm của sinh viên từ bảng Score
            Score score = student.getScore();
            if (score != null) {
                session.delete(score);
            }

            // Xóa sinh viên
            session.delete(student);
            
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }


    public List<Student> getAllStudentsWithScores() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Student s join fetch s.score", Student.class).list();
        }
    }

    public Student searchById(int studentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Student.class, studentId);
        }
    }

    public List<Student> getTopStudents(int limit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Student s join fetch s.score order by (s.score.score1 + s.score.score2 + s.score.score3) desc", Student.class)
                          .setMaxResults(limit)
                          .list();
        }
    }

    public static boolean update(Student updatedStudent) {
        if (updatedStudent == null) {
            return false;
        }

        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            
            // Kiểm tra xem kết nối có đóng không và mở lại nếu cần
            if (!session.isConnected()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }

            transaction = session.beginTransaction();

            session.update(updatedStudent);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }



}

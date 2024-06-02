package Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "Score")
public class Score {

    @Id
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "id")
    private Student student;

    @Column(name = "score1")
    private double score1;

    @Column(name = "score2")
    private double score2;

    @Column(name = "score3")
    private double score3;

    // Constructor không tham số bắt buộc cho Hibernate
    public Score() {}

    // Constructor có tham số
    public Score(int id, double score1, double score2, double score3) {
        this.id = id;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore1() {
        return score1;
    }

    public void setScore1(double score1) {
        this.score1 = score1;
    }

    public double getScore2() {
        return score2;
    }

    public void setScore2(double score2) {
        this.score2 = score2;
    }

    public double getScore3() {
        return score3;
    }

    public void setScore3(double score3) {
        this.score3 = score3;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

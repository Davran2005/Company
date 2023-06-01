package peaksoft.entyti;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "courses")
@Setter
@Getter
@NoArgsConstructor

public class Course {
    @Id
    @GeneratedValue(generator = "course_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "course_gen",sequenceName = "course_seq",allocationSize = 1)
    private Long id;
    private String courseName;
    private LocalDate dateOfStar;
    private String description;
    @ManyToOne(cascade = {DETACH,REFRESH,MERGE,REMOVE})
    private Company company;
    @ManyToOne(cascade = {DETACH,MERGE,REFRESH})
    private Instructor instructor;
    @ManyToMany(cascade = {DETACH,MERGE,REFRESH})
    private List<Group>groups;

    @ManyToOne(cascade = {DETACH,REFRESH,MERGE})
    private Lesson lesson;

    public Course(String courseName, LocalDate dateOfStar, String description) {
        this.courseName = courseName;
        this.dateOfStar = dateOfStar;
        this.description = description;
    }
}

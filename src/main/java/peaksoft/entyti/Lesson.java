package peaksoft.entyti;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "lessons")
@Setter
@Getter
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(generator = "lesson_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "lesson_gen",sequenceName = "lesson_seq",allocationSize = 1)
    private Long id;
    private String lessonName;
    @OneToMany(mappedBy = "lesson",cascade = {DETACH,REFRESH,MERGE,REMOVE})
    private List<Course>courses;
    @OneToMany(mappedBy = "lesson",cascade = {DETACH,MERGE,REFRESH})
    private List<Task>tasks;

    public Lesson(String lessonName) {
        this.lessonName = lessonName;
    }
}

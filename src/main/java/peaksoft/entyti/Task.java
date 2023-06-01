package peaksoft.entyti;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "task")
@Setter
@Getter
public class Task {
    @Id
    @GeneratedValue(generator = "task_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "task_gen",sequenceName = "task_seq",allocationSize = 1)
private Long id;
private String taskName;
private String taskText;
private LocalDate deadLine;
@ManyToOne(cascade = {DETACH,MERGE,REFRESH,REMOVE})
private Lesson lesson;
}

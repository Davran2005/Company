package peaksoft.entyti;

import jakarta.persistence.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(generator = "instructor_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "instructor_gen",sequenceName = "instructor_seq",allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    @ManyToMany(mappedBy = "instructors",cascade = {DETACH,MERGE,REFRESH,REMOVE})
    private List<Company>companies;
    @OneToMany(mappedBy = "instructor",cascade = {DETACH,MERGE,REFRESH})
    private List<Course>courses;


}

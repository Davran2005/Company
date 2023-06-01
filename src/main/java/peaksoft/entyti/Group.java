package peaksoft.entyti;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "groups")
@Setter
@Getter
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(generator = "group_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "group_gen", sequenceName = "group_seq", allocationSize = 1)
    private Long id;
    private String groupName;
    private String imageLink;
    private String description;
    @ManyToMany(mappedBy = "groups",cascade = {DETACH,REFRESH,MERGE,REMOVE})
    private List<Course> courses;
    @OneToMany(mappedBy = "group",cascade = {DETACH,REFRESH,MERGE})
    private List<Student>students;

    public Group(String groupName, String imageLink, String description) {
        this.groupName = groupName;
        this.imageLink = imageLink;
        this.description = description;
    }
}

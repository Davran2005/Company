package peaksoft.entyti;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor

public class Company {
    @Id
    @GeneratedValue(generator = "company_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "company_gen",sequenceName = "company_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
    @OneToMany(mappedBy = "company",cascade = {DETACH,MERGE,REFRESH})
    private List<Course>courses;
    @ManyToMany(cascade = {DETACH,MERGE,REFRESH})
    private List<Instructor>instructors;

    public Company(String name, String country, String address, String phoneNumber) {
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}

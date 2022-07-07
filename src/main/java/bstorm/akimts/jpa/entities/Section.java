package bstorm.akimts.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "section")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Section {

    @Id
    @Column(name = "section_id")
    private int id;
    @Column(name = "section_name")
    private String name;

    @OneToMany(mappedBy = "section")
    private List<Student> studentList;

    @ManyToMany
    @JoinTable(
            name = "course_section",
            joinColumns = @JoinColumn(name = "section_id"), // ref à la table de Section
            inverseJoinColumns = @JoinColumn(name = "course_id") // ref à la table de Course
    )
    private Set<Course> courses = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "delegate_id")
    private Student delegue;

    public Section(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

package bstorm.akimts.jpa.entities;

import jakarta.persistence.*;
import lombok.*;


//@Table(name = "student")
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    // Uni-directionnelles - 1 var dans 1 classe
    // Bi-directionnelles - 1 var dans chacune des 2 classes => mappedBy

    // OneToOne - OneToOne
    // OneToMany - ManyToOne
    // ManyToMany - ManyToMany

    // dans le cas bi-direct
    // entity forte / faible

    @Id
    @Column(name = "student_id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @OneToOne(mappedBy = "delegue")
    private Section delegueDe;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", section=" +  (section==null ? null : section.getName()) +
                '}';
    }
}

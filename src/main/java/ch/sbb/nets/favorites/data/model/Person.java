package ch.sbb.nets.favorites.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.List;

@Entity
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
  @SequenceGenerator(sequenceName = "person_seq", allocationSize = 1, name = "person_seq")
  @Column(name = "ID")
  private Long id;

  @Column(name = "FIRSTNAME")
  private String firstName;
  @Column(name = "LASTNAME")
  private String lastName;

  @JsonIgnore
  @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
  private List<PersonTag> tags;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public List<PersonTag> getTags() {
    return tags;
  }
}

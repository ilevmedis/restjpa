package ch.sbb.nets.favorites.data.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "PERSONTAG")
public class PersonTag {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persontag_seq")
  @SequenceGenerator(sequenceName = "persontag_seq", allocationSize = 1, name = "persontag_seq")
  @Column(name = "ID")
  private Long id;

  @JsonManagedReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "PERSONID")
  private Person person;

  @Column(name = "TAG")
  private String tag;

  public String getTag() {
    return tag;
  }

  public void setTag(final String tag) {
    this.tag = tag;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(final Person person) {
    this.person = person;
  }

  public Long getId() {
    return id;
  }
}

package ch.sbb.nets.favorites.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Labels {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "label_seq")
  @SequenceGenerator(sequenceName = "label_seq", allocationSize = 1, name = "label_seq")
  @Column(name = "ID")
  private Long id;

  @Column(name = "KEY")
  private String key;
  @Column(name = "VALUE")
  private String value;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FAVOURITEID")
  private Favourite favourite;

  public Long getId() {
    return id;
  }

  public String getKey() {
    return key;
  }

  public void setKey(final String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(final String value) {
    this.value = value;
  }

  public Favourite getFavourite() {
    return favourite;
  }

  public void setFavourite(final Favourite favourite) {
    this.favourite = favourite;
  }
}

package ch.sbb.nets.favorites.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;
import java.util.List;

@Entity
public class Favourite {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favourite_seq")
  @SequenceGenerator(sequenceName = "favourite_seq", allocationSize = 1, name = "favourite_seq")
  private Long id;

  @Column(name="USERID")
  private String userid;
  @Column(name="PROFILE")
  private String profile;
  @Column(name="DESCR")
  private String descr;
  @Column(name="OBJECTDATA")
  @Lob
  private String objectData;
  @Column(name="VERSION")
  @Version
  private int version;
  @Column(name="CHANGESTAMP")
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date changeStamp;

  @JsonIgnore
  @OneToMany(mappedBy = "favourite", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Labels> labels;

  public String getUserid() {
    return userid;
  }

  public void setUserid(final String userid) {
    this.userid = userid;
  }

  public String getProfile() {
    return profile;
  }

  public void setProfile(final String profile) {
    this.profile = profile;
  }

  public String getDescr() {
    return descr;
  }

  public void setDescr(final String descr) {
    this.descr = descr;
  }

  public String getObjectData() {
    return objectData;
  }

  public void setObjectData(final String objectData) {
    this.objectData = objectData;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(final int version) {
    this.version = version;
  }

  public Long getId() {
    return id;
  }

  public Date getChangeStamp() {
    return changeStamp;
  }

  public void setChangeStamp(final Date changeStamp) {
    this.changeStamp = changeStamp;
  }

  public List<Labels> getLabels() {
    return labels;
  }

  @PrePersist
  @PreUpdate
  void updateChangeStamp() {
    changeStamp = new Date();
  }
}

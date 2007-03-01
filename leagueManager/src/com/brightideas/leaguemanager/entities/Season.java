/*
 * Season.java
 *
 * Created on 1 de marzo de 2007, 12:01
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.brightideas.leaguemanager.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class Season
 * 
 * @author asanchis
 */
@Entity
@Table(name = "SEASONS")
public class Season implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /** Creates a new instance of Season */
    public Season() {
    }

    /**
     * Gets the id of this Season.
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id of this Season to the specified value.
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    private final static int CREATED=0;
  private final static int ACTIVE=1;
  private final static int FINISHED=2;
  @Temporal(value = TemporalType.DATE)
    private Date initDate;
  @Temporal(value = TemporalType.DATE)
    private Date endDate;
  private String seasonId;
  @OneToMany(mappedBy = "season")
    private ArrayList<LeagueGroup> groups = new ArrayList<LeagueGroup> ();
  private int state=CREATED;

  
  public void setInitDate(Date initDate) {
    this.initDate = initDate;
  }

  public void setEndDate(Date endDate) {

    this.endDate = endDate;
  }

  public void setSeasonId(String seasonId) {
    this.seasonId = seasonId;
  }

  public void setGroups(ArrayList groups) {
    this.groups = groups;
  }

  public void setState(int state) {
    this.state = state;
  }

  public Date getInitDate() {
    return initDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public String getSeasonId() {
    return seasonId;
  }

  public ArrayList getGroups() {
    return groups;
  }

  public int getState() {
    return state;
  }

  public void initSeason() {
    Iterator<LeagueGroup> iter = groups.listIterator();
    while (iter.hasNext()) {
      LeagueGroup group = iter.next();
      group.initGroup();
    }
    state=ACTIVE;
  }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this Season.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Season object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Season)) {
            return false;
        }
        Season other = (Season)object;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "com.brightideas.leaguemanager.entities.Season[id=" + id + "]";
    }
    
}

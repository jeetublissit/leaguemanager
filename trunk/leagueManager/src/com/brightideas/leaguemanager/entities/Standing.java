/*
 * Standing.java
 *
 * Created on 1 de marzo de 2007, 12:10
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.brightideas.leaguemanager.entities;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity class Standing
 * 
 * @author asanchis
 */
@Entity
@Table(name = "Standings")
public class Standing implements Serializable,Comparator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /** Creates a new instance of Standing */
    public Standing() {
    }

    /**
     * Gets the id of this Standing.
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id of this Standing to the specified value.
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    private LeagueGroup leagueGroup;
  private Set<Player> players = new TreeSet<Player> (this);


  public int compare(Object o1, Object o2) {
    Player player1 = (Player) o1;
    Player player2 = (Player) o2;
    int puntosPlayer1 = calculaPuntosJugador(player1);
    int puntosPlayer2 = calculaPuntosJugador(player2);
    if (puntosPlayer1 < puntosPlayer2) {
      return -1;
    }
    if (puntosPlayer1 > puntosPlayer2) {
      return 1;
    }
    if (puntosPlayer1 == puntosPlayer2) {
      return 0;
    }
    return 0;
  }

  /**
   * calculaPuntosJugador
   *
   * @return int
   */
  
  private int calculaPuntosJugador(Player player) {
    int points = 0;
    Set<Match> matches = leagueGroup.getFinishedMatches(player);
    Iterator<Match> iter = matches.iterator();
    while (iter.hasNext()) {
      Match match = iter.next();
      if (match.getState() == Match.PLAYED) {
        if (match.getWinner().equals(player)) {
          points = points + 3;
        }
        else {
          if (match.getPlayerSets(player) != null)
          {
            points++;
          }
        }
      }
    }

    return 0;
  }

  public boolean equals(Object obj) {
    return false;
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
     * Determines whether another object is equal to this Standing.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Standing object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
 /*   @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Standing)) {
            return false;
        }
        Standing other = (Standing)object;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) return false;
        return true;
    }*/

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "com.brightideas.leaguemanager.entities.Standing[id=" + id + "]";
    }
    
}

/*
 * Match.java
 *
 * Created on 1 de marzo de 2007, 10:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.brightideas.leaguemanager.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class Match
 *
 * @author asanchis
 */
@Entity
@Table(name = "MATCHES")
public class Match implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /** Creates a new instance of Match */
    public Match() {
    }
    
    /**
     * Gets the id of this Match.
     * @return the id
     */
    public Long getId() {
        return this.id;
    }
    
    /**
     * Sets the id of this Match to the specified value.
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
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
     * Determines whether another object is equal to this Match.  The result is
     * <code>true</code> if and only if the argument is not null and is a Match object that
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
/*    @Override
      public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Match)) {
            return false;
        }
        Match other = (Match)object;
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
        return "com.brightideas.leaguemanager.entities.Match[id=" + id + "]";
    }
    public static final int PENDING = 0;
    public static final int PLAYED_NOT_VALIDATED = 1;
    public static final int PLAYED = 2;
    
    
  
    @ManyToOne
    private Player player1;
    @ManyToOne
    private Player player2;
     
    private Integer player1Sets;
    private Integer player2Sets;
    @OneToOne
    private Player winner;
    @OneToOne
    private Player firstValidator;
    @OneToOne
    private Player secondValidator;
    @Temporal(value = TemporalType.DATE)
    @Column(name = "VALIDATIONDATE")
    private Date validationDate;
    @Temporal(value = TemporalType.DATE)
    private Date secondValidationDate;
    @Temporal(value = TemporalType.DATE)
    @Column(name = "PLAYDATE")
    private Date playDate;
    @ManyToOne
    private LeagueGroup leagueGroup;
    private int groupState = PENDING;
    
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    
    public void setPlayer1Sets(Integer player1Sets) {
        this.player1Sets = player1Sets;
    }
    
    public void setPlayer2Sets(Integer player2Sets) {
        this.player2Sets = player2Sets;
    }
    
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    
    public void setPlayDate(Date playDate) {
        this.playDate = playDate;
    }
    
    public void setGroup(LeagueGroup group) {
        this.leagueGroup = group;
    }
    
    public void setFirstValidator(Player firstValidator) {
        this.firstValidator = firstValidator;
    }
    
    public void setSecondValidator(Player secondValidator) {
        this.secondValidator = secondValidator;
    }
    
    public void setSecondValidationDate(Date secondValidationDate) {
        this.secondValidationDate = secondValidationDate;
    }
    
    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }
    
    public void setState(int state) {
        this.groupState = state;
    }
    
    public Player getPlayer1() {
        return player1;
    }
    
    public Player getPlayer2() {
        return player2;
    }
    
    public Integer getPlayer1Sets() {
        return player1Sets;
    }
    
    public Integer getPlayer2Sets() {
        return player2Sets;
    }
    
    public Player getWinner() {
        return winner;
    }
    
    public Date getPlayDate() {
        return playDate;
    }
    
    public LeagueGroup getGroup() {
        return leagueGroup;
    }
    
    public Player getFirstValidator() {
        return firstValidator;
    }
    
    public Player getSecondValidator() {
        return secondValidator;
    }
    
    public Date getSecondValidationDate() {
        return secondValidationDate;
    }
    
    public Date getValidationDate() {
        return validationDate;
    }
    
    public int getState() {
        return groupState;
    }
    
    public boolean equals(Object match) {
        if (! (match instanceof Match)) {
            return false;
        }
        
        if (this.leagueGroup.equals( ( (Match) match).getGroup())) {
            if (this.player1.equals( ( (Match) match).getPlayer1())) {
                if (this.player2.equals( ( (Match) match).getPlayer2())) {
                    return true;
                }
            }
            if (this.player1.equals( ( (Match) match).getPlayer2())) {
                if (this.player2.equals( ( (Match) match).getPlayer1())) {
                    return true;
                }
            }
        }
        return false;
    }        
    
    public void setResult(Integer player1Sets, Integer player2Sets, Date playDate) {
        this.player1Sets = player1Sets;
        this.player2Sets = player2Sets;
        this.playDate = playDate;
        if (player1Sets.intValue() == 3) {
            winner = player1;
        }
        if (player2Sets.intValue() == 3) {
            winner = player2;
        }
        groupState = PLAYED_NOT_VALIDATED;
    }
    
    public Integer getPlayerSets(Player player) {
        if (player1.equals(player)) {
            return player1Sets;
        }
        if (player2.equals(player)) {
            return player2Sets;
        }
        return null;
    }
}

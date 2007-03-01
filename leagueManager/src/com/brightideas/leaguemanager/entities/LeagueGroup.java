/*
 * GroupE.java
 *
 * Created on 1 de marzo de 2007, 9:57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.brightideas.leaguemanager.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity class GroupE
 *
 * @author asanchis
 */
@Entity
@Table(name = "GROUPS")
public class LeagueGroup implements Serializable {

    @OneToOne(mappedBy = "leagueGroup")
    private Standing standing;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Set<Player> players = new HashSet<Player> ();
    @ManyToOne
    private Season season;
    private Set<Match> matches = new HashSet<Match> ();
    @ManyToOne
    private Player coordinador;

    @Column(name = "GROUPNAME")
    private String groupName;
    
    /** Creates a new instance of GroupE */
    public LeagueGroup() {
    }
    
    /**
     * Gets the id of this GroupE.
     * @return the id
     */
    public Long getId() {
        return this.id;
    }
    
    /**
     * Sets the id of this GroupE to the specified value.
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
     * Determines whether another object is equal to this GroupE.  The result is
     * <code>true</code> if and only if the argument is not null and is a GroupE object that
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeagueGroup)) {
            return false;
        }
        LeagueGroup other = (LeagueGroup)object;
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
        return "com.brightideas.leaguemanager.entities.GroupE[id=" + id + "]";
    }
    
    
    
    
    public void setPlayers(Set players) {
        this.players = players;
    }
    
    public void setSeason(Season season) {
        this.season = season;
    }
    
    public void setMatches(Set matches) {
        this.matches = matches;
    }
    
    public void setCoordinador(Player coordinador) {
        this.coordinador = coordinador;
    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public Set getPlayers() {
        return players;
    }
    
    public Season getSeason() {
        return season;
    }
    
    public Set getMatches() {
        return matches;
    }
    
    public Player getCoordinador() {
        return coordinador;
    }
    
    /**
     * initGroup
     */
    public void initGroup() {
        Iterator<Player> iter = players.iterator();
        while (iter.hasNext()) {
            Player player1 = (Player) iter.next();
            Iterator<Player> iter2 = players.iterator();
            while (iter2.hasNext()) {
                Player player2 = (Player) iter2.next();
                if (! (player1.equals(player2))) {
                    Match match = new Match();
                    match.setPlayer1(player1);
                    match.setPlayer2(player2);
                    match.setGroup(this);
                    matches.add(match);
                }
            }
        }
    }
    
    /**
     * getMatches
     *
     * @param player Player
     * @return Set
     */
    public Set getMatches(Player player) {
        Iterator iter = matches.iterator();
        Set<Match> matches=new HashSet<Match>();
        while (iter.hasNext()) {
            Match match = (Match) iter.next();
            if (match.getPlayer1().equals(player) || match.getPlayer2().equals(player)) {
                matches.add(match);
            }
        }
        return matches;
    }
    
    public Set<Match> getFinishedMatches(Player player) {
        Iterator iter = matches.iterator();
        Set<Match> matches=new HashSet<Match>();
        while (iter.hasNext()) {
            Match match = (Match) iter.next();
            if ( (match.getPlayer1().equals(player) ||
                    match.getPlayer2().equals(player)) && (match.getWinner() != null)) {
                matches.add(match);
            }
        }
        return matches;
    }
}
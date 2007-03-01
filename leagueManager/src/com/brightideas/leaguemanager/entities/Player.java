/*
 * Player.java
 *
 * Created on 1 de marzo de 2007, 11:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.brightideas.leaguemanager.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.swing.ImageIcon;

/**
 * Entity class Player
 * 
 * @author asanchis
 */
@Entity
@Table(name = "PLAYERS")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /** Creates a new instance of Player */
    public Player() {
    }

    /**
     * Gets the id of this Player.
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id of this Player to the specified value.
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "NAME")
    private String name;
    @Column(name = "FISTSURNAME")
    private String firstSurname;
    @Column(name = "SECONDSURNAME")
    private String secondSurname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String phone;
    @ManyToOne    
    private LeagueGroup leagueGroup;
    @Column(name = "RAQUET")
    private String raquet;
    @Column(name = "LOGIN")
    private String login;
    @OneToMany(mappedBy = "coordinador")
    private ArrayList<LeagueGroup> gruposCoordinados;
    @Column(name = "PHOTO")
    private ImageIcon photo;
    
    public LeagueGroup getLeagueGroup() {
        return leagueGroup;
    }
    
    public void setLeagueGroup(LeagueGroup leagueGroup) {
        this.leagueGroup = leagueGroup;
    }
    
    public ArrayList<LeagueGroup> getGrupoCoordinado() {
        return gruposCoordinados;
    }
    
    public void setGrupoCoordinado(ArrayList<LeagueGroup> grupoCoordinado) {
        this.gruposCoordinados = grupoCoordinado;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }
    
    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setRaquet(String raquet) {
        this.raquet = raquet;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public void setPhoto(ImageIcon photo) {
        this.photo = photo;
    }
    
    public String getName() {
        return name;
    }
    
    public String getFirstSurname() {
        return firstSurname;
    }
    
    public String getSecondSurname() {
        return secondSurname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getRaquet() {
        return raquet;
    }
    
    public String getLogin() {
        return login;
    }
    
    
    public ImageIcon getPhoto() {
        return photo;
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
     * Determines whether another object is equal to this Player.  The result is 
     * <code>true</code> if and only if the argument is not null and is a Player object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (this.id == null){
            return super.equals(object);
        }
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player)object;
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
        return "com.brightideas.leaguemanager.entities.Player[id=" + id + "]";
    }
    
}

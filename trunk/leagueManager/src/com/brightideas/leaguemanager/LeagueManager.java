

package com.brightideas.leaguemanager;

import com.brightideas.leaguemanager.entities.LeagueGroup;
import com.brightideas.leaguemanager.entities.Season;
import java.util.Date;

import com.brightideas.leaguemanager.entities.Player;
import java.util.ArrayList;
import java.util.*;
import com.brightideas.leaguemanager.entities.Match;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class LeagueManager {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("leagueManagerPU");
  public LeagueManager() {
    Season season=new Season();
    season.setInitDate(new Date());
    season.setEndDate(new Date());
    season.setSeasonId("1");
    for (int i=0;i < 10 ; i++ ) {
      LeagueGroup group=new LeagueGroup();
      group.setSeason(season);
      group.setGroupName(""+i);
      for (int j=0;j< 10; j++) {
        Player player=new Player();
        player.setLeagueGroup(group);
        player.setName("Player "+ i + j);
        group.getPlayers().add(player);
      }
      season.getGroups().add(group);
    }
    season.initSeason();
    
    System.out.println("season:"+season.getSeasonId());
    ArrayList<LeagueGroup> groups=season.getGroups();
    Iterator<LeagueGroup> iter = groups.iterator();
    while (iter.hasNext()) {
      LeagueGroup group = iter.next();
      System.out.println("group:"+group.getGroupName());
      Set matches=group.getMatches();
      Iterator<Match> iter2 = matches.iterator();
      while (iter2.hasNext()) {
        Match match = (Match) iter2.next();
        System.out.println(match.getPlayer1().getName()+" VS "+match.getPlayer2().getName());
      }
    }
  }
  public static void main(String[] args) {
    LeagueManager l=new LeagueManager();

  }

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            // TODO:
            // em.persist(object);    em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

        
}

package ism.ouzeon.RepositorieDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ism.ouzeon.Entities.Chambre;
import ism.ouzeon.Enums.Etat;
import ism.ouzeon.Enums.TypeChambre;

public class ChambreRepositorie extends Repositorie<Chambre> {

  @Override
  public int generateId() {

    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery("select max(id) from chambre");
      if (rs.next()) {
        int id = rs.getInt(1) + 1;
        return id;
      }
    } catch (ClassNotFoundException e) {
      System.out.println("Erreur de chargement du Driver");
    } catch (SQLException e) {
      System.out.println("Erreur de Connexion a votre BD");
    }
    return 0;
  }

  @Override
  public boolean save(Chambre chambre) {
    int nbre = 0;
    Connection conn = null;
    int id = generateId();
    chambre.setId(id);
    chambre.setNumero(generateNumero(id, "CHA"));
    try {

      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");

      Statement statement = conn.createStatement();
      nbre = statement.executeUpdate(
          String.format("INSERT INTO `chambre` ( `numero`, `numEtage`, `type` , `etat`, `pavillonId`) VALUES ('%s','%d','%s', '%s', '%d')",
              chambre.getNumero(), chambre.getNumEtage(), chambre.getType(), chambre.getEtat(), chambre.getPavillon().getId()));
              System.out.println(chambre.getPavillon().getNumero());
      System.out.println("Connexion Bd etablie");
    } catch (ClassNotFoundException e) {
      System.out.println("Erreur de chargement du Driver");
    } catch (SQLException e) {
      System.out.println("Erreur de Connexion a votre BD");
    }
    return nbre == 0;
  }

  @Override
  public List<Chambre> selectAll() {

    List<Chambre> chambres = new ArrayList<>();
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery("SELECT * FROM `chambre`");
      while (rs.next()) {
        Chambre ch = new Chambre();
        ch.setId(rs.getInt("id"));
        ch.setNumero(rs.getString("numero"));
        ch.setNumEtage(rs.getInt("numEtage"));
        ch.setType(TypeChambre.getValue(rs.getString("type")));
        ch.setEtat(Etat.getValue(rs.getString("etat")));
        chambres.add(ch);
      }
      System.out.println("Connexion Bd etablie");
    } catch (ClassNotFoundException e) {
      System.out.println("Erreur de chargement du Driver");
    } catch (SQLException e) {
      System.out.println("Erreur de Connexion a votre BD");
    }
    return chambres;

  }

  @Override
  public Chambre retrouver(String mat) {
    List<Chambre> chambres = selectAll();
    for (Chambre chambre : chambres) {
      if (chambre.getNumero().compareTo(mat) == 0) {
        return chambre;
      }
    }
    return null;
  }
  public List<Chambre> getChambresPavillon(int id ) {
    List<Chambre> chambres = new ArrayList<>();
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery(String.format("SELECT * FROM `chambre` WHERE `pavillonId`= %d",id));
      while (rs.next()) {
        Chambre ch = new Chambre();
        ch.setId(rs.getInt("id"));
        ch.setNumero(rs.getString("numero"));
        ch.setNumEtage(rs.getInt("numEtage"));
        ch.setType(TypeChambre.getValue(rs.getString("type")));
        ch.setEtat(Etat.getValue(rs.getString("etat")));
        chambres.add(ch);
      }
      System.out.println("Connexion Bd etablie");
    } catch (ClassNotFoundException e) {
      System.out.println("Erreur de chargement du Driver");
    } catch (SQLException e) {
      System.out.println("Erreur de Connexion a votre BD");
    }
    return chambres;
  }

  @Override
  public boolean update(Chambre chambre) {
    Chambre ch = retrouver(chambre.getNumero());
    // ch.getPavillon().getTabChambre().remove(chambre);
    int nbre = 0;
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");

      Statement statement = conn.createStatement();
      nbre = statement.executeUpdate(
          String.format(
              "UPDATE `chambre` SET `numEtage` = %d, `type` = '%s', `etat` = '%s', `pavillonId` = %d WHERE `numero` = '%s'",
              chambre.getNumEtage(), chambre.getType().toString(), chambre.getEtat().toString(), chambre.getPavillon().getId(), ch.getNumero()));
      System.out.println("Connexion Bd etablie");
    } catch (ClassNotFoundException e) {
      System.out.println("Erreur de chargement du Driver");
    } catch (SQLException e) {
      System.out.println("Erreur de Connexion a votre BD");
    }
    return nbre == 0;
  }

    @Override
  public boolean archiver(Chambre chambre) {
    Chambre ch = retrouver(chambre.getNumero());
    chambre.setEtat(Etat.Archiver);
    // ch.getPavillon().getTabChambre().remove(chambre);
    int nbre = 0;
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");

      Statement statement = conn.createStatement();
      nbre = statement.executeUpdate(
          String.format(
              "UPDATE `chambre` SET `etat` = '%s' WHERE `numero` = '%s'",
              chambre.getEtat().toString(), ch.getNumero()));
      System.out.println("Connexion Bd etablie");
    } catch (ClassNotFoundException e) {
      System.out.println("Erreur de chargement du Driver");
    } catch (SQLException e) {
      System.out.println("Erreur de Connexion a votre BD");
    }
    return nbre == 0;
  }

}

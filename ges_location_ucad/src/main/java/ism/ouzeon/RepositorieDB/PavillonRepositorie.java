package ism.ouzeon.RepositorieDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ism.ouzeon.Entities.Pavillon;

public class PavillonRepositorie extends Repositorie<Pavillon> {

  @Override
  public int generateId() {
    //recuperer la dernier id de la base de donnéé

    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery("select max(id) from pavillon");
      if (rs.next()) {
        int id=rs.getInt(1) + 1;
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
  public Pavillon retrouver(String mat) {
    List<Pavillon> pavillons =selectAll();
    for (Pavillon pavillon : pavillons) {
      if (pavillon.getNumero().compareTo(mat) == 0) {
        return pavillon;
      }
    }
    return null;
  }

  @Override
  public boolean save(Pavillon pavillon) {
    int nbre = 0;
    Connection conn = null;
    int id = generateId();
    pavillon.setId(id);
    pavillon.setNumero(generateNumero(id, "PAV"));
    try {

      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");

      Statement statement = conn.createStatement();
      nbre = statement.executeUpdate(
          String.format("INSERT INTO `pavillon` ( `numero`, `nbrEtage`) VALUES ('%s', '%d')",
              pavillon.getNumero(), pavillon.getNbrEtage()));
      System.out.println("Connexion Bd etablie");
    } catch (ClassNotFoundException e) {
      System.out.println("Erreur de chargement du Driver");
    } catch (SQLException e) {
      System.out.println("Erreur de Connexion a votre BD");
    }
    return nbre == 0;

  }

  @Override
  public List<Pavillon> selectAll() {
    List<Pavillon> pavillons = new ArrayList<>();
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery("SELECT * FROM `pavillon`");
      while (rs.next()) {
        Pavillon pavillon = new Pavillon();
        pavillon.setId(rs.getInt("id"));
        pavillon.setNumero(rs.getString("numero"));
        pavillon.setNbrEtage(rs.getInt("nbrEtage"));
        pavillons.add(pavillon);
      }
      System.out.println("Connexion Bd etablie");
    } catch (ClassNotFoundException e) {
      System.out.println("Erreur de chargement du Driver");
    } catch (SQLException e) {
      System.out.println("Erreur de Connexion a votre BD");
    }
    return pavillons;
  }

  @Override
  public boolean update(Pavillon pavillon) {
    Pavillon pav=retrouver(pavillon.getNumero());
    int nbre = 0;
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");

      Statement statement = conn.createStatement();
      nbre = statement.executeUpdate(
          String.format("UPDATE `pavillon` SET `nbrEtage` = '%d' WHERE `numero` = '%s'",
               pavillon.getNbrEtage(), pav.getNumero()));
      System.out.println("Connexion Bd etablie");
    } catch (ClassNotFoundException e) {
      System.out.println("Erreur de chargement du Driver");
    } catch (SQLException e) {
      System.out.println("Erreur de Connexion a votre BD");
    }
    return nbre == 0;
  }
  




}

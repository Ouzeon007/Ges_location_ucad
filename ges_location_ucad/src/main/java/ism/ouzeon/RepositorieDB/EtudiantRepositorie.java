package ism.ouzeon.RepositorieDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ism.ouzeon.Entities.Boursier;
import ism.ouzeon.Entities.Chambre;
import ism.ouzeon.Entities.Etudiant;
import ism.ouzeon.Entities.Loge;
import ism.ouzeon.Entities.NonBoursier;
import ism.ouzeon.Enums.Etat;
import ism.ouzeon.Enums.TypeBourse;

public class EtudiantRepositorie extends Repositorie<Etudiant> {

  @Override
  public int generateId() {
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery("select max(id) from etudiant");
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
  public Etudiant retrouver(String mat) {

    Connection conn = null;
    Etudiant etudiant = null;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery(String.format("SELECT * FROM `etudiant` WHERE `matricule` = '%s'", mat));
      while (rs.next()) {

        String matricule = rs.getString("matricule");
        String nom = rs.getString("nom");
        String prenom = rs.getString("prenom");
        String email = rs.getString("email");
        String telephone = rs.getString("telephone");
        LocalDate dateNaiss = rs.getDate("dateNaiss").toLocalDate();
        TypeBourse typeBourse = rs.getString("typeBourse") != null ? TypeBourse.getValue(rs.getString("typeBourse"))
            : null;
        String adresse = rs.getString("addresse");
        Integer chambreId = rs.getInt("chambreId");

        if (adresse == null && chambreId != null) {
          etudiant = new Loge(matricule, nom, prenom, email, telephone, typeBourse, dateNaiss);
        } else if (adresse == null) {
          etudiant = new Boursier(matricule, nom, prenom, email, telephone, typeBourse, dateNaiss);
        } else {
          etudiant = new NonBoursier(matricule, nom, prenom, email, telephone, dateNaiss, adresse);
        }

      }

      return etudiant;

    } catch (ClassNotFoundException e) {
      System.out.println("Erreur de chargement du Driver");
    } catch (SQLException e) {
      System.out.println("Erreur de Connexion a votre BD" + e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  // @Override
  // public Etudiant retrouver(String mat) {

  // Connection conn = null;
  // try {
  // Class.forName("com.mysql.cj.jdbc.Driver");
  // conn =
  // DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad",
  // "root", "");
  // Statement statement = conn.createStatement();
  // ResultSet rs = statement.executeQuery(String.format("SELECT * FROM `etudiant`
  // WHERE `matricule` = '%s'", mat));
  // Etudiant etudiant = null; // Modifier pour éviter de retourner le même
  // étudiant plusieurs fois
  // while (rs.next()) {
  // // Logique pour créer un nouvel étudiant en fonction de son type
  // if (rs.getString("type").equals("NonBoursier")) {
  // etudiant = new NonBoursier();
  // ((NonBoursier) etudiant).setAddresse(rs.getString("addresse"));
  // } else if (rs.getString("type").equals("Boursier")) {
  // etudiant = new Boursier();
  // ((Boursier)
  // etudiant).setTypeBourse(TypeBourse.getValue(rs.getString("type")));
  // } else {
  // etudiant = new Etudiant();
  // }

  // etudiant.setMatricule(rs.getString("matricule"));
  // etudiant.setNom(rs.getString("nom"));
  // etudiant.setPrenom(rs.getString("prenom"));
  // etudiant.setEmail(rs.getString("email"));
  // etudiant.setTelephone(rs.getString("telephone"));
  // etudiant.setDateNaiss(rs.getDate("dateNaiss").toLocalDate());

  // System.out.println(etudiant);
  // }

  // return etudiant;

  // } catch (ClassNotFoundException e) {
  // System.out.println("Erreur de chargement du Driver");
  // } catch (SQLException e) {
  // System.out.println("Erreur de Connexion a votre BD");
  // }
  // return null;
  // }

  @Override
  public boolean save(Etudiant etudiant) {
    int nbre = 0;
    Connection conn = null;
    int id = generateId();
    etudiant.setId(id);
    etudiant.setMatricule(generateNumero(id, "ETU"));
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");

      Statement statement = conn.createStatement();
      if (etudiant instanceof Loge) {
        nbre = statement.executeUpdate(
            String.format(
                "INSERT INTO `etudiant` ( `matricule`, `nom`, `prenom`, `email`, `telephone`, `dateNaiss`, `typeBourse`, `chambreId`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%d')",
                etudiant.getMatricule(), etudiant.getNom(), etudiant.getPrenom(), etudiant.getEmail(),
                etudiant.getTelephone(), etudiant.getDateNaiss(), ((Boursier) etudiant).getTypeBourse(), 0));
        return true;
      } else if (etudiant instanceof Boursier) {
        nbre = statement.executeUpdate(
            String.format(
                "INSERT INTO `etudiant` ( `matricule`, `nom`, `prenom`, `email`, `telephone`, `dateNaiss`, `typeBourse`) VALUES ('%s','%s','%s','%s','%s','%s','%s')",
                ((Boursier) etudiant).getMatricule(), ((Boursier) etudiant).getNom(), ((Boursier) etudiant).getPrenom(),
                ((Boursier) etudiant).getEmail(),
                ((Boursier) etudiant).getTelephone(), ((Boursier) etudiant).getDateNaiss().toString(),
                ((Boursier) ((Boursier) etudiant)).getTypeBourse().toString()));
        return true;
      } else if (etudiant instanceof NonBoursier) {
        nbre = statement.executeUpdate(
            String.format(
                "INSERT INTO `etudiant` ( `matricule`, `nom`, `prenom`, `email`, `telephone`, `dateNaiss`, `addresse`) VALUES ('%s','%s','%s','%s','%s','%s','%s')",
                etudiant.getMatricule(), etudiant.getNom(), etudiant.getPrenom(), etudiant.getEmail(),
                etudiant.getTelephone(), etudiant.getDateNaiss(),
                ((NonBoursier) etudiant).getAddresse()));
        return true;
      }

      System.out.println("Connexion Bd etablie");
    } catch (ClassNotFoundException e) {
      System.out.println("Erreur de chargement du Driver");
    } catch (SQLException e) {
      System.out.println("Erreur de Connexion a votre BD" + e.getMessage());
      e.printStackTrace();
    }
    return nbre == 0;
  }

  @Override
  public List<Etudiant> selectAll() {
    List<Etudiant> etudiants = new ArrayList<>();
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery("SELECT * FROM `etudiant`");
      while (rs.next()) {
        Etudiant etudiant = new Etudiant();
        etudiant.setMatricule(rs.getString("matricule"));
        etudiant.setNom(rs.getString("nom"));
        etudiant.setPrenom(rs.getString("prenom"));
        etudiant.setEmail(rs.getString("email"));
        etudiant.setTelephone(rs.getString("telephone"));
        etudiant.setDateNaiss(rs.getDate("dateNaiss").toLocalDate());
        if (etudiant instanceof NonBoursier) {
          ((NonBoursier) etudiant).setAddresse(rs.getString("addresse"));
        }
        if (etudiant instanceof Boursier) {
          ((Boursier) etudiant).setTypeBourse(TypeBourse.getValue(rs.getString("type")));
        }
        etudiants.add(etudiant);
      }
      System.out.println("Connexion Bd etablie");
    } catch (ClassNotFoundException e) {
      System.out.println("Erreur de chargement du Driver");
    } catch (SQLException e) {
      System.out.println("Erreur de Connexion a votre BD");
    }
    return etudiants;
  }

  // function qui affecter un etudiant a une chambre

  @Override
  public boolean affecter(Etudiant etudiant, Chambre chambre) {
    // ch.getPavillon().getTabChambre().remove(chambre);
    int nbre = 0;
    Connection conn = null;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");

      Statement statement = conn.createStatement();
      if (etudiant instanceof Loge) {
        if (chambre.getEtat().compareTo(Etat.Activer) == 0) {
          nbre = statement.executeUpdate(
              String.format("UPDATE `etudiant` SET `chambreId` = %d WHERE `etudiant`.`matricule` = '%s'",
                  chambre.getId(),
                  etudiant.getMatricule()));
          System.out.println("Connexion Bd etablie");
        }

      } else {

        return false;
      }
    } catch (ClassNotFoundException e) {
      System.out.println("Erreur de chargement du Driver");
    } catch (SQLException e) {
      System.out.println("Erreur de Connexion a votre BD " + e.getMessage());
      e.printStackTrace();
    }
    return nbre == 0;

  }

  @Override
  public List<Etudiant> getEtudiantChambres(int id) {
    List<Etudiant> etudiants = new ArrayList<>();
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ges_chambre_ucad", "root", "");
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery(String.format("SELECT * FROM `etudiant` WHERE `chambreId`= %d", id));
      while (rs.next()) {
        Etudiant etudiant = new Etudiant();
        etudiant.setMatricule(rs.getString("matricule"));
        etudiant.setNom(rs.getString("nom"));
        etudiant.setPrenom(rs.getString("prenom"));
        etudiant.setEmail(rs.getString("email"));
        etudiant.setTelephone(rs.getString("telephone"));
        etudiant.setDateNaiss(rs.getDate("dateNaiss").toLocalDate());
        etudiants.add(etudiant);
      }
      System.out.println("Connexion Bd etablie");
    } catch (ClassNotFoundException e) {
      System.out.println("Erreur de chargement du Driver");
    } catch (SQLException e) {
      System.out.println("Erreur de Connexion a votre BD");
    }
    return etudiants;
  }
}

// public Boursier selectBoursier(ResultSet rs) {
// try {
// Boursier etudiant = new Boursier();
// etudiant.setMatricule(rs.getString("matricule"));
// etudiant.setNom(rs.getString("nom"));
// etudiant.setPrenom(rs.getString("prenom")); // Fixed setPrenom parameter
// etudiant.setEmail(rs.getString("email"));
// etudiant.setTelephone(rs.getString("telephone"));
// etudiant.setDateNaiss(rs.getDate("dateNaiss").toLocalDate());
// etudiant.setTypeBourse(TypeBourse.getValue(rs.getString("type")));
// return etudiant;
// } catch (SQLException e) {
// System.out.println("Erreur de Connexion a votre BD");
// return null;
// }
// }

// public NonBoursier selectNonBoursier(ResultSet rs) {
// try {
// NonBoursier etudiant = new NonBoursier();
// etudiant.setMatricule(rs.getString("matricule"));
// etudiant.setNom(rs.getString("nom"));
// etudiant.setPrenom(rs.getString("prenom")); // Fixed setPrenom parameter
// etudiant.setEmail(rs.getString("email"));
// etudiant.setTelephone(rs.getString("telephone"));
// etudiant.setDateNaiss(rs.getDate("dateNaiss").toLocalDate());
// etudiant.setAddresse(rs.getString("addresse"));
// return etudiant;
// } catch (SQLException e) {
// System.out.println("Erreur de Connexion a votre BD");
// return null;
// }
// }

// public Loge selectLoge(ResultSet rs) {
// try {
// Loge etudiant = new Loge();
// etudiant.setMatricule(rs.getString("matricule"));
// etudiant.setNom(rs.getString("nom"));
// etudiant.setPrenom(rs.getString("prenom")); // Fixed setPrenom parameter
// etudiant.setEmail(rs.getString("email"));
// etudiant.setTelephone(rs.getString("telephone"));
// etudiant.setDateNaiss(rs.getDate("dateNaiss").toLocalDate());
// etudiant.setTypeBourse(TypeBourse.getValue(rs.getString("type")));
// return etudiant;
// } catch (SQLException e) {
// System.out.println("Erreur de Connexion a votre BD");
// return null;
// }
// }

// @Override
// public Etudiant getById(int id) {
// int nbre = 0;
// Connection conn = null;
// EtudiantLoge el = new EtudiantLoge();
// try {
// Class.forName("com.mysql.cj.jdbc.Driver");
// conn =
// DriverManager.getConnection("jdbc:mysql://localhost:3306/gesucad","root",
// "");
// Statement statement = conn.createStatement();
// ResultSet rs = statement.executeQuery(String.format("SELECT * FROM etudiant
// WHERE id= '%d';",id));
// while (rs.next()){
// el.setId(rs.getInt("id"));
// el.setNomComplet(rs.getString("nomComplet"));
// el.setMatricule(rs.getString("matricule"));
// el.setMail(rs.getString("mail"));
// el.setTelephone(rs.getString("telephone"));
// el.setDatenaiss(formatDate(rs.getString("dateNaiss")));
// el.setBourse(TypeDeBourse.valueOf(rs.getString("bourse")));
// }
// System.out.println("Connexion Bd etablie");
// return el;
// } catch (ClassNotFoundException var5) {
// System.out.println("Erreur de chargement du Driver");
// } catch (SQLException var6) {
// System.out.println("Erreur de Connexion a votre BD");
// }
// return null;
// }
// }

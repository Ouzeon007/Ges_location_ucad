package ism.ouzeon.Core;

import java.util.List;

import ism.ouzeon.Entities.Chambre;
import ism.ouzeon.Entities.Etudiant;

public interface iRepositorie<T> {

    boolean save(T object);

    List<T> selectAll();

    int generateId();

    T retrouver(String mat);

    String generateNumero(int nbre, String format);

    boolean update(T object);

    boolean archiver(T object);

    List<Chambre> getChambresPavillon(int id);

    List<Etudiant> getEtudiantChambres(int id );

    boolean affecter(Etudiant etudiant, Chambre chambre);
}

package ism.ouzeon.Entities;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pavillon {
    private int id;
    private String numero;
    private int nbrEtage;
    private static int nbrPavillon;
    private List<Chambre> tabChambre = new ArrayList<>();
    
    public static int getNbrPavillon() {
        return ++nbrPavillon;
    }

    public void add(Chambre chambre) {
        tabChambre.add(chambre);
    }

    @Override
    public String toString() {
        return "Pavillon [numero=" + numero + ", nbrEtage=" + nbrEtage + "]";
    }

}

package ism.ouzeon.Enums;

public enum Etat {
    Activer,Archiver;

    public static Etat getValue(String etat) {
        for (Etat e : Etat.values()) {
            if (e.name().compareTo(etat) == 0) {
                return e;
            }
        }
        return null;
    }
}

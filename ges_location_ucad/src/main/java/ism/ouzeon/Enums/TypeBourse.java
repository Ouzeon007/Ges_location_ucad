package ism.ouzeon.Enums;

public enum TypeBourse {
    Demie_Bourse,Bourse_Entier;

    public static TypeBourse getValue(String type) {
        for (TypeBourse t : TypeBourse.values()) {
            if (t.name().compareTo(type) == 0) {
                return t;
            }
        }
        return null;
    }
}

package ism.ouzeon.Enums;



public enum TypeChambre {
    Individuel, ADeux;

    public static TypeChambre getValue(String type) {
        for (TypeChambre t : TypeChambre.values()) {
            if (t.name().compareTo(type)==0) {
                return t;
            }
        }
        return null;
    }
}

package model;

public record Party(String codParty) {
    public Party {
        if (codParty == null || codParty.length() > 20) {
            throw new IllegalArgumentException("The codParty passed is null or too long: it mus be under 20 characters");
        }
    }
}

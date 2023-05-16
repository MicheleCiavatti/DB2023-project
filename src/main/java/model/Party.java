package model;

public record Party(String codParty) {
    public Party {
        Checker.checkCode(codParty);
    }
}

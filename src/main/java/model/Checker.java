package model;

public class Checker {
    
    private static final String ERROR = ": the string is null or too long";
    private static final int NAME_LENGTH = 50;
    private static final int DESC_LENGTH = 255;
    private static final int CODE_LENGTH = 20;


    public static void checkName(String name) {
        if (name == null || name.length() > NAME_LENGTH) {
            throw new IllegalArgumentException(name + ERROR);
        }
    }

    public static void checkDesc(String desc) {
        if (desc == null || desc.length() > DESC_LENGTH) {
            throw new IllegalArgumentException(desc + ERROR);
        }
    }

    public static void checkCode(String code) {
        if (code == null || code.length() > CODE_LENGTH) {
            throw new IllegalArgumentException(code + ERROR);
        }
    }

}

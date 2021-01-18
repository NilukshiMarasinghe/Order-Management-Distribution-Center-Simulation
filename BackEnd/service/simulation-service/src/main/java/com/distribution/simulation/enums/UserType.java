package com.distribution.simulation.enums;

public enum UserType {

    ADMIN_USER("AU"),
    CLIENT_USER("CU");

    private String code;

    private UserType(String code) {
        this.code = code;
    }

    public String toString() {
        return this.code;
    }

    public String getCode() {
        return this.code;
    }

    public static UserType getEnum(String code) {
        UserType[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            UserType item = var1[var3];
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }

        return null;
    }
}

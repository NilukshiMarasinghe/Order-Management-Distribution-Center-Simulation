package com.distribution.worker.enums;


import com.distribution.worker.exception.ComplexValidationException;

public enum UserStatus {

    ACTIVE("Active", "A"),
    INACTIVE("Inactive", "I"),
    DELETED("Deleted", "D"),
    PENDING_ACTIVATION("Pending Activation", "PEA"),
    TEMP_LOCKED_BAD_CREDENTIALS("Temp Locked Bad Credentials", "TELBC");


    private String label;
    private String value;

    private UserStatus(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static UserStatus getEnum(String value) {
        for (UserStatus item : UserStatus.values()) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public static UserStatus getStatus(String value) {
        switch (value) {
            case "A":
                return UserStatus.ACTIVE;
            case "I":
                return UserStatus.INACTIVE;
            case "D":
                return UserStatus.DELETED;
            case "PEA":
                return UserStatus.PENDING_ACTIVATION;
            case "TELBC":
                return UserStatus.TEMP_LOCKED_BAD_CREDENTIALS;
            default:
                throw new ComplexValidationException("user", "getStatus.invalidStatus");
        }
    }
}

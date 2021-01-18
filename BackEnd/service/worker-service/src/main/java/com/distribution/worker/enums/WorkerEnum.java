package com.distribution.worker.enums;


import com.distribution.worker.exception.ComplexValidationException;

public enum WorkerEnum {

    PICKER("PI"),
    PACKER("PA");

    private String code;


    private WorkerEnum(String code) {
        this.code = code;

    }
    public String getCode() {
        return this.code;
    }

    public static WorkerEnum getWorkerEnum(String code) {
        switch (code) {
            case "PI":
                return WorkerEnum.PICKER;
            case "PA":
                return WorkerEnum.PACKER;

            default:
                throw new ComplexValidationException("worker", "invalidCode");
        }
    }
}

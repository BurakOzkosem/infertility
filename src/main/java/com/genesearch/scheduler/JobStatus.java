package com.genesearch.scheduler;

public enum JobStatus {

    SUCCESS(1),
    FAILED(2),
    WORKING(3);

    private int code;

    JobStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public  static JobStatus getByCode(int code) {
        for( JobStatus obj : values()) {
            if(code == obj.getCode()) {
                return obj;
            }
        }
        return null;
    }
}

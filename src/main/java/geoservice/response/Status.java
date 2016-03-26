package geoservice.response;

/**
 * Created by Alexey Ostrikov on 26/03/2016.
 */
public enum Status {
    SUCCESS("Success"),
    ERROR("Error");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    }

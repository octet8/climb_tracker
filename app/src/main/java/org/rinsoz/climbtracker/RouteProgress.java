package org.rinsoz.climbtracker;

public enum RouteProgress {
    NOT_TRY("NOT_TRY"), FAILED("FAILED"), WITH_STOP("WITH_STOP"), SUCCESS("SUCCESS");

    private final String value;

    RouteProgress(String value) {
        this.value = value;
    }

    public static RouteProgress fromValue(String value) {
        if (value != null) {
            for (RouteProgress progress : values()) {
                if (progress.value.equals(value)) {
                    return progress;
                }
            }
        }

        // you may return a default value
        return getDefault();
        // or throw an exception
        // throw new IllegalArgumentException("Invalid color: " + value);
    }

    public String toValue() {
        return value;
    }

    public static RouteProgress getDefault() {
        return NOT_TRY;
    }
}

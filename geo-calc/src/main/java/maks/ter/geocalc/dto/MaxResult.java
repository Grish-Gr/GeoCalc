package maks.ter.geocalc.dto;

public enum MaxResult {
    RESULT_10(10, "топ-10 регионов"),
    RESULT_20(20, "топ-20 регионов"),
    RESULT_30(30, "топ-30 регионов"),
    ALL(Integer.MAX_VALUE, "все регионы");

    final int value;
    final String description;

    MaxResult(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}

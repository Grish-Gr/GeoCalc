package maks.ter.geocalc.dto;

public enum PurposeCalc {
    EDUCATION("Получение образование в IT-сфере"),
    EMPLOYEE("Получение работы в IT-сфере");

    final String title;

    PurposeCalc(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

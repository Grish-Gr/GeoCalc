package maks.ter.geocalc.dto;

public enum PurposeCalc {
    EDUCATION("Получение образование в IT-сфере"),
    EMPLOYEE("Получение работы в IT-сфере"),
    MIGRATION_INFO_REGION("Переезд в регион с высоким индексом цифровизации");

    final String title;

    PurposeCalc(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

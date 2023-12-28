package maks.ter.geocalc.dto;

public enum EmploymentDataType {
    PROPOSED_SALARY("Получение образование в IT-сфере"),
    VACANCIES("Получение работы в IT-сфере");

    final String title;

    EmploymentDataType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

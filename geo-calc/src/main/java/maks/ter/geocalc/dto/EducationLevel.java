package maks.ter.geocalc.dto;

public enum EducationLevel {
    CVO("Средне специальное образование – Колледж", "Все программы СПО"),
    SPEC("Высшее образование – Бакалавриат/Специалитет", "Бакалавриат и специалитет"),
    MASTER("Высшее образование – Магистратура", "");

    final String description;
    final String code;

    EducationLevel(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }
}

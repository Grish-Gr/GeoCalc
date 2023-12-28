package maks.ter.geocalc.dto;

public enum EdPriority {
    AMOUNT_CONTRACT("Стоимость обучения"),
    COUNT_BUDGET("Количество бюджетных мест"),
    CONTRACT_COUNT("Количество контрактных мест");

    final String title;

    EdPriority(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

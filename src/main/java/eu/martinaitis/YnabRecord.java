package eu.martinaitis;

public record YnabRecord(
        java.time.LocalDate date,
        String payee,
        String memo,
        String inflow,
        String outflow
) {
}

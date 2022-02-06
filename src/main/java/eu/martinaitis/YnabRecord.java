package eu.martinaitis;

import java.time.LocalDate;

public class YnabRecord {
    public final java.time.LocalDate date;
    public final String payee;
    public final String memo;
    public final String inflow;
    public final String outflow;

    public YnabRecord(LocalDate date, String payee, String memo, String inflow, String outflow) {
        this.date = date;
        this.payee = payee;
        this.memo = memo;
        this.inflow = inflow;
        this.outflow = outflow;
    }


}

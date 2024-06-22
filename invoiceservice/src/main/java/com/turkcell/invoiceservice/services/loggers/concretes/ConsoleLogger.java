package com.turkcell.invoiceservice.services.loggers.concretes;

import com.turkcell.invoiceservice.services.loggers.abstracts.LoggerBase;

public class ConsoleLogger extends LoggerBase {
    @Override
    public void log(String message) {
        String info = String.join("\n", "| CONSOLE LOG STARTS |", message, "| CONSOLE LOG ENDED |");
        System.out.println(info);
    }
}

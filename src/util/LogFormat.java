package util;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormat extends Formatter {

    private static final String FORMAT = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

    @Override
    public String format(LogRecord record) {
        return String.format(FORMAT,
                new Date(record.getMillis()),
                record.getLevel().getLocalizedName(),
                record.getMessage());
    }

}

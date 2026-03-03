package edu.unizg.foi.uzdiz.vvugrinec21.app.pomocnici;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public final class FormatIspisa {
    private static final DecimalFormat DECIMAL_2;
    private static final DecimalFormat INTEGER;

    static {
        DecimalFormatSymbols sym = new DecimalFormatSymbols();
        sym.setGroupingSeparator('.'); 
        sym.setDecimalSeparator(',');  

        DECIMAL_2 = new DecimalFormat("#,##0.00", sym);
        DECIMAL_2.setGroupingUsed(true);

        INTEGER = new DecimalFormat("#,##0", sym);
        INTEGER.setGroupingUsed(true);
    }

    private FormatIspisa() {}

    public static String intFmt(Integer v) {
        return v == null ? "-" : INTEGER.format(v);
    }

    public static String dec2Fmt(BigDecimal v) {
        return v == null ? "-" : DECIMAL_2.format(v);
    }
}

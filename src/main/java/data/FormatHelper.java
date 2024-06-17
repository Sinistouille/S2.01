package data;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FormatHelper {

    private FormatHelper() {
        throw new ClassCastException("Utility class");
    }

    //public final static DecimalFormat df = new DecimalFormat("0.00");

    public static String DecimalFormat(float f){
        return Math.round(f*100)/100.0 + "";
    }
}
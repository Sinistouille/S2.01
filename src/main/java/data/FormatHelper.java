package data;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FormatHelper {
    public final static DecimalFormat df = new DecimalFormat("0.00");
    public String DecimalFormat(float f){
        return df.format(f);
    }

}

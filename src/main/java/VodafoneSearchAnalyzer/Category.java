package VodafoneSearchAnalyzer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public enum Category {

    CARE_CENTER("centrum péče", "Care Center", 2),
    OFFER("nabídka", "Public web", 16),
    SELF_SERVICE("samoobsluha", "WSC", 17),
    WORLD_MANUALS("nastavení", "WorldManuals", 3),
    OVER_ALL("vše", "", 0);

    private String name;
    private String reportName;
    private int publicWebParameter;

    Category(String name, String reportName, int publicWebParameter) {
        this.name = name;
        this.reportName = reportName;
        this.publicWebParameter = publicWebParameter;
    }

    public String getName() {
        return name;
    }

    public String getReportName() {
        return reportName;
    }

    public int getPublicWebParameter() {
        return publicWebParameter;
    }

}

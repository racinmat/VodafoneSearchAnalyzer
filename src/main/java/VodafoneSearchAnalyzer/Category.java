package VodafoneSearchAnalyzer;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public enum Category {

    CARE_CENTER("centrum péče", "Centrum pece", 2),
    OFFER("nabídka", "Nabidka", 16),
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

    public static Category createFromReportName(String reportName) throws InvalidArgumentException {
        for (Category category : Category.values()) {
            if (category.getReportName().equals(reportName)) {
                return category;
            }
        }
        String[] arguments = {"Not known report name for category: "+reportName};
        throw new InvalidArgumentException(arguments);
    }

}

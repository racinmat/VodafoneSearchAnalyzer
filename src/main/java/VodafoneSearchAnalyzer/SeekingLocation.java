package VodafoneSearchAnalyzer;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public enum SeekingLocation {

    PUBLIC_WEB("Public web:");

    private String reportName;

    SeekingLocation(String reportName) {
        this.reportName = reportName;
    }

    public String getReportName() {
        return reportName;
    }

}

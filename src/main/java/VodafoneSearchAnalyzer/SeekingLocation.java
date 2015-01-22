package VodafoneSearchAnalyzer;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.Serializable;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public enum SeekingLocation implements Serializable {

    PUBLIC_WEB("Public web");

    private String reportName;

    SeekingLocation(String reportName) {
        this.reportName = reportName;
    }

    public String getReportName() {
        return reportName;
    }

    public static SeekingLocation createFromReportName(String reportName) throws InvalidArgumentException {
        for (SeekingLocation seekingLocation : SeekingLocation.values()) {
            if (seekingLocation.getReportName().equals(reportName)) {
                return seekingLocation;
            }
        }
        String[] arguments = {"Not known report name for seeking location: "+reportName};
        throw new InvalidArgumentException(arguments);
    }

}

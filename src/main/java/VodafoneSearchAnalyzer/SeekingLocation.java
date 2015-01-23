package VodafoneSearchAnalyzer;

import VodafoneSearchAnalyzer.Seekers.PublicWebSeeker;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.Serializable;

/**
 * Created by Azathoth on 21. 1. 2015.
 */
public enum SeekingLocation implements Serializable {

    PUBLIC_WEB("Public web", PublicWebSeeker.class);

    private String reportName;
    private Class seekerClass;

    SeekingLocation(String reportName, Class seekerClass) {
        this.reportName = reportName;
        this.seekerClass = seekerClass;
    }

    public String getReportName() {
        return reportName;
    }

    public Class getSeekerClass() {
        return seekerClass;
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

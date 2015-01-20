import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class OverAllSeeker {

    private List<VodafoneAbstractSeeker> seekers;

    public OverAllSeeker(List<VodafoneAbstractSeeker> seekers) {
        this.seekers = seekers;
    }

    public OverAllSeeker(VodafoneAbstractSeeker... seekers) {
        this.seekers = new ArrayList<VodafoneAbstractSeeker>();
        for (int i = 0; i < seekers.length; i++) {
            this.seekers.add(seekers[i]);
        }
    }

    public List<SearchResult> searchForWords(List<String> words, int results) throws IOException {
        List<SearchResult> resultWebsites = new ArrayList<SearchResult>();
        for (String word : words)
            for (VodafoneAbstractSeeker seeker : seekers) {
                System.out.println("Seeking word "+word+" by seeker "+seeker.getClass()+".");
                List<SearchResult> resultsForOneWors = seeker.searchForWord(word, results);
                resultWebsites.addAll(resultsForOneWors);
            }
        return resultWebsites;
    }
}

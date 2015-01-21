package VodafoneSearchAnalyzer;

import VodafoneSearchAnalyzer.ExcelOutput.WriteExcel;
import VodafoneSearchAnalyzer.SearchedWord.SearchWordsProvider;
import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import VodafoneSearchAnalyzer.Seekers.PublicWebSeeker;

import java.util.Collection;
import java.util.List;

/**
 Copyright (c) 2014, Matěj Račinský
 All rights reserved.

 Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following
 disclaimer in the documentation and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
public class MainAnalyzer {

    public static void main(String[] args) throws Exception {
        List<SearchedWord> wordsToBeSearched = SearchWordsProvider.getWordsToBeSearched();
        VodafoneSearchAnalyzer.OverAllSeeker seeker = new VodafoneSearchAnalyzer.OverAllSeeker(new PublicWebSeeker(10));
        List<AbstractSearchResult> results = seeker.searchForWords(wordsToBeSearched);
        for (SearchedWord word : wordsToBeSearched) {
            System.out.println(word.toString());
        }
        for (VodafoneSearchAnalyzer.AbstractSearchResult result : results) {
            System.out.println(result.toString());
        }
        WriteExcel test = new WriteExcel();
        test.setOutputFile("Output.xls");
        test.write(results);
        System.out.println("Please check the result file under Output.xls ");
    }

}

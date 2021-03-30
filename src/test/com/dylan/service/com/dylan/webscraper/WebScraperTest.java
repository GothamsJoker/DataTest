package com.dylan.service.com.dylan.webscraper;

import com.dylan.webscraper.WebScraper;
import org.junit.Test;

import java.io.IOException;

public class WebScraperTest {

    @Test
    public void it_connects_to_uri() {
        // just makes sure the scraper doesn't throw an exception for a valid URI
        try {
            WebScraper.scrape("https://en.wikipedia.org/wiki/Joker_(character)", "mw" +
                            "-body-content", "p");
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

}

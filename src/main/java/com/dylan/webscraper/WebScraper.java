package com.dylan.webscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Currently for simple testing of gathering data from a site and managing the text.
 * TODO: Remove  element tags, trim text, store in database table type = LONGTEXT......, present
 * text as paragraph in website.
 *
 * Currently set to run in the class as no need to use else where for now.
 */
public class WebScraper {

    public static void main(String[] args) {
        // Use Jsoup tp connect to URL and grab all <p> elements from a class
        //https://en.wikipedia.org/wiki/Joker_(character)
        //"mw-body-content"
        //"p"
        System.out.println("\tWebScraper");
        System.out.println("\tProgram designed to scrape site text and store it in a file.\n");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a vald URI: ");
            String uri = scanner.nextLine();
            System.out.print("Enter desired Element path: ");
            String element = scanner.nextLine();
            System.out.print("Enter desired element tag: ");
            String tag = scanner.nextLine();
            System.out.print("Enter file path to save the output to (leave blank to output to " +
                    "stdout): ");
            String outputFile = scanner.nextLine();

            Collection<String> data = scrape(uri, element, tag);
            // no file, write to stdout
            if (outputFile.trim().isEmpty()) {
                System.out.println("\n\tScraped Data:");
                data.stream()
                        .map(s -> "\t" + s)
                        .forEach(System.out::println);
                return;
            }

            File f = new File(outputFile);
            if (!f.createNewFile()) {
                throw new IllegalStateException(String.format("failed to create output file %z" ,
                        outputFile));
            }

            FileWriter fileWriter = new FileWriter(f);
            for (String d : data) {
                fileWriter.write(d);
            }
        } catch (Exception e) {
            throw new RuntimeException("failed to scrape site", e);
        }
    }

    public static Collection<String> scrape(String uri, String element, String tag) throws IOException {
        Document doc = Jsoup.connect(uri).get();

        // Gather data
        return doc.getElementsByClass(element).stream()
                // get by tag
                .map(e -> e.getElementsByTag(tag))
                // getElementsByTag returns an instance of Elements, flatten the
                // multiple Stream<Elements> down to one Stream<Element>
                .flatMap(Elements::stream)
                // map to raw html string
                .map(Element::toString)
                // remove html tags
                .map(WebScraper::sanitize)
                .collect(Collectors.toList());
    }

    private static String sanitize(String rawHtml) {
        return rawHtml.replaceAll("<p>", "")
                .replaceAll("<a", "")
                .replaceAll("</p>", "")
                .replaceAll("</a>", "")
                .replaceAll("<b>", "")
                .replaceAll("</b", "")
                .replaceAll("<i>", "")
                .replaceAll("</i>", "");
    }

}



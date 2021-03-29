package com.dylan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
Currently for simple testing of gathering data from a site and managing the text.
TO DO: Remove  element tags, trim text, store in database table type = LONGTEXT...
..., present text as paragraph in website.

Currently set to run in the class as no need to use else where for now.
 */

public class HtmlUnit {

    public static void main(String[] args) throws IOException {

        // Grab user input for url, class, and element type
        System.out.println("Program designed to place site text into a text file.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a valid HTML:");
        String htmlInput = scanner.nextLine();
        System.out.println("Enter Element class path:");
        String path = scanner.nextLine();
        System.out.println("Enter desired element type:");
        String ele = scanner.nextLine();

        // Use Jsoup tp connect to URL and grab all <p> elements from a class
        //https://en.wikipedia.org/wiki/Joker_(character)
        //"mw-body-content"
        //"p"
        Document doc = Jsoup.connect(htmlInput).get();
        Elements titles = doc.getElementsByClass(path);
        for (Element title : titles) {
            titles = title.getElementsByTag(ele);
        }

        // Grab user input for file name
        Scanner txtInput = new Scanner(System.in);
        System.out.println("Enter txt file");
        String fileName = txtInput.nextLine();

        // Attempt to create file
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("New file created as" + file.getName());

                // Write to file and remove element tags probably a better way to do this such as string list
                FileWriter myWriter = new FileWriter(fileName);
                myWriter.write(String.valueOf(titles).replaceAll("<p>", "").replaceAll("<a",
                        "").replaceAll("</p>", "")
                        .replaceAll("</a>", "").replaceAll("<b>", "")
                        .replaceAll("</b", "").replaceAll("<i>", "")
                        .replaceAll("</i>", ""));
                System.out.println("File has been written to");


            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Grab file and output data written
        File file = new File(fileName);
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(data);

        }
    }
}



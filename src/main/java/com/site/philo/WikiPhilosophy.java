package com.site.philo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class WikiPhilosophy {

    private final static String CONSTANT_URL_HEAD = "https://en.wikipedia.org";

    final static List<String> visited = new ArrayList<String>();
    final static WikiFetcher wf = new WikiFetcher();


    /**
     * Tests a conjecture about Wikipedia and Philosophy.
     * <p>
     * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
     * <p>
     * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     * that does not exist, or when a loop occurs
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Logger LOG = Logger.getLogger(WikiPhilosophy.class);
        String destination = "https://en.wikipedia.org/wiki/Philosophy";
        String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        LOG.info("Starting...");
        testConjecture(destination, source, 10);
    }

    /**
     * Starts from given URL and follows first link until it finds the destination or exceeds the limit.
     *
     * @param destination
     * @param source
     * @throws IOException
     */
    public static void testConjecture(String destination, String source, int limit) throws IOException {
        WikiFetcher fetcher = new WikiFetcher();
        while (limit > 0) {

            if (!source.startsWith("https")) {
                source = String.format("%s%s", CONSTANT_URL_HEAD, source);
            }
            Elements elements = fetcher.fetchWikipedia(source);
            while (!elements.isEmpty()) {
                String goal = "";
                WikiNodeIterable node = new WikiNodeIterable(elements.first());
                elements.remove(0);
                Iterator<Node> it = node.iterator();
                while (it.hasNext()) {
                    Node innerNode = it.next();
                    if (innerNode.hasAttr("href")) {
                        goal = innerNode.attr("href");
                        System.out.println(source);
                        break;
                    }
                }
                if (!goal.isEmpty()) {
                    source = goal;
                    break;
                }
            }
            limit--;
        }
    }
}

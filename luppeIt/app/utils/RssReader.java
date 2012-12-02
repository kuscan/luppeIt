package utils;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import models.share.Share;
import play.Logger;

import javax.sql.rowset.spi.XmlReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/26/12
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class RssReader {



    public static List<Share> readRssFeed(String feedUrl) throws IOException {

        List<Share> shares = new ArrayList<Share>();


        XmlReader reader = null;
        try {
            URL url = new URL(feedUrl);
            reader = new XmlReader(url);

            SyndFeed feed;
            feed = new SyndFeedInput().build(reader);
            Logger.info("Feed Title: " + feed.getAuthor());

            for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
                SyndEntry entry = (SyndEntry) i.next();

                Share share = new Share();
                share.setTitle(entry.getTitle());
                share.setDescription(entry.getDescription().toString());
                share.setContent(entry.getContents().toString());
                Logger.info("\nEntry title      : " + entry.getTitle() + "\n" +
                            "Entry author     : " + entry.getAuthor() + "\n" +
                            "Entry description: " + entry.getDescription() + "\n" +
                            "Entry content    : " + ((SyndContent)entry.getContents().get(0)).getValue() + "\n" +
                            "Entry link       : " + entry.getLink() + "\n" +
                            "Entry links      : " + entry.getLinks() + "\n" +
                            "Entry pubDate    : " + entry.getPublishedDate() + "\n");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                reader.close();
        }





        return null;
    }
}

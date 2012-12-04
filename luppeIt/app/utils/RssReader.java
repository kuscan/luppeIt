package utils;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import config.LuppeItConstants;
import models.share.RssResource;
import models.share.Share;
import play.Logger;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/26/12
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class RssReader {

    public static List<Share> readRssFeed(RssResource rssResource) throws IOException {

        List<Share> shares = new ArrayList<Share>();

        XmlReader reader = null;
        try {
            URL url = new URL(rssResource.getUrl());
            reader = new XmlReader(url);

            SyndFeed feed;
            feed = new SyndFeedInput().build(reader);

            for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
                SyndEntry entry = (SyndEntry) i.next();
                Share share = new Share();

                share.setTitle(entry.getTitle());
                share.setDescription(entry.getDescription().getValue());
                share.setUrl(entry.getLink());
                share.setAuthor(entry.getAuthor());
                share.setLuppeCount(0);
                share.setDigCount(0);
                share.setViewCount(0);
                share.setCategoryId(rssResource.getCategoryId());
                share.setShareStatusId(LuppeItConstants.SHARE_STATUS_ACTIVE);
                share.setRssResourceId(rssResource.getRssResourceId());
                share.setUserId(LuppeItConstants.RSS_READER_USER_ID);
                share.setLastModifiedDate(entry.getPublishedDate().getTime());
                share.setContent("");
                for (Object content: entry.getContents()) {
                    share.setContent(share.getContent() + ((SyndContent)content).getValue());
                }
                shares.add(share);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                reader.close();
        }

        return shares;
    }
}

package job;

import database.dao.rssresource.RssResourceDAO;
import database.dao.share.ShareDAO;
import models.share.RssResource;
import models.share.Share;
import play.Logger;
import play.jobs.Every;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import utils.RssReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/26/12
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Every("5mn")
@OnApplicationStart
public class UpdateFeedJob extends Job {

    public void doJob() {
        Logger.info("UpdateFeedJob started!");

        /*
            Get rss resources which need to be checked
         */
        List<RssResource> rssResources = RssResourceDAO.getRssResourcesToParse();

        for (RssResource rssResource: rssResources) {
            try {
                Logger.info("Parse RssResource started: " + rssResource.getRssResourceName());
                List<Share> shares = ShareDAO.getSharesByRssResourceId(rssResource.getRssResourceId());
                if (shares == null) {
                	shares = new ArrayList<Share>();
                }
                Logger.info("Number of shares in DB: " + ((Integer)shares.size()).toString());
                List<Share> sharesFromRss = RssReader.readRssFeed(rssResource);
                Logger.info("Number of shares from RSS : " + ((Integer)sharesFromRss.size()).toString());

                for (Share share: sharesFromRss) {
                    if (!shares.contains(share)) {
                        Logger.info("Share to insert into DB: " + share.getTitle() + " " + share.getUrl());
                        Integer shareId = ShareDAO.addShare(share);
                        
                        if (shareId != null) {
                        	String title = share.getTitle().toLowerCase().replace(".", "").replace(",", "").replace(":", "").replace(";", "").replace("?", "").replace("!", "").replace("\"", "").replace("'", "");
                        	String [] titleWords = title.split("\\s+");
                        	
                        	for (String word: titleWords)
                        	{
                        		ShareDAO.addTagToShare("${LPT}$" + word, shareId);
                        	}
                        }
                        
                    }
                }
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, rssResource.getUpdateIntervalMinute());
                RssResourceDAO.updateRssResourceNextFeedDate(rssResource.getRssResourceId(), cal.getTimeInMillis());
                Logger.info("Parse RssResource ended!");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Logger.info("UpdateFeedJob finished!");
    }

}

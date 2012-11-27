package job;

import play.Logger;
import play.jobs.Every;
import play.jobs.Job;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/26/12
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Every("5mn")
public class UpdateFeedJob extends Job {

    public void doJob() {
        Logger.info("UpdateFeedJob started!");

       /* *//*
            Get all active rssResources
         *//*
        List<RssResource> rssResources = RssResourceDAO.getAllActiveRssResources();

        *//*
            Iterate over rssResources
         *//*
        for (RssResource rssResource: rssResources) {

        }*/

        Logger.info("UpdateFeedJob finished!");
    }

}

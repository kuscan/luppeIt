package controllers.share;

import config.LuppeItConstants;
import play.Logger;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Scope;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/17/12
 * Time: 6:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShareController extends Controller {

    @Before
    static void before() {
        Logger.info(LuppeItConstants.BASE_URL);
        renderArgs.put("baseUrl", LuppeItConstants.BASE_URL);
        renderArgs.put("pageTitle", LuppeItConstants.MAIN_PAGE_TITLE);
    }

    @Before
    static void checkLogin() {
        if (Scope.Session.current().contains("userId")) {
            renderArgs.put("user", Cache.get("user" + Scope.Session.current().get("userId")));
        } else {
        }
    }

    /*public static void newShare() {
        HashMap<String, String> arguments = new HashMap<String, String>();

        List<Category> categories = CategoryDAO.getAllCategoriesOrderByName();

        arguments.put("urlValue", "");
        arguments.put("categoryValue", "");
        arguments.put("titleValue", "");
        arguments.put("descriptionValue", "");
        renderTemplate(NavigationConstants.submitLinkPage, arguments, categories);
    }

    public static void submitNewLink(String url,
                                     String category,
                                     String title,
                                     String description) {

        HashMap<String, String> arguments = new HashMap<String, String>();
        Boolean hasError = false;
        URL shareUrl = null;
        try {
            shareUrl = new URL(url);
        } catch (MalformedURLException e) {
            arguments.put("urlFormatError", LuppeItConstants.SUBMIT_LINK_URL_FORMAT_ERROR_MESSAGE);
            hasError = true;
        }
        if (category == null || category.equals("")) {
            arguments.put("categoryError", LuppeItConstants.SUBMIT_LINK_CATEGORY_ERROR_MESSAGE);
            hasError = true;
        }
        if (title == null || title.equals("")) {
            arguments.put("titleError", LuppeItConstants.SUBMIT_LINK_TITLE_ERROR_MESSAGE);
            hasError = true;
        }
        if (description == null || description.equals("")) {
            arguments.put("descriptionError", LuppeItConstants.SUBMIT_LINK_DESCRIPTION_ERROR_MESSAGE);
            hasError = true;
        }

        String resourceUrl = "";
        try {
            resourceUrl = shareUrl.getHost();
            List<Resource> resources = Resource.find("resourceName = ?", resourceUrl).fetch();
            if (resources.size() > 0) {
                if (resources.get(0).getResourceStatusId() != LuppeItConstants.RESOURCE_STATUS_ACTIVE) {
                    arguments.put("resourceNotActiveError", LuppeItConstants.SUBMIT_LINK_RESOURCE_NOT_ACTIVE_ERROR_MESSAGE);
                    hasError = true;
                }
            } else {
                Resource resource = new Resource();
                resource.setResourceName(resourceUrl);
                resource.setResourceStatusId(LuppeItConstants.RESOURCE_STATUS_ACTIVE);
                resource.save();
            }
        } catch (Exception e) {
            hasError = true;
        }

        if (hasError) {
            List<Category> categories = Category.findAll();

            Logger.info(category);

            arguments.put("urlValue", url);
            arguments.put("categoryValue", category);
            arguments.put("titleValue", title);
            arguments.put("descriptionValue", description);

            renderTemplate(NavigationConstants.submitLinkPage, arguments, categories);
        }

        Share share = new Share();
        share.setUrl(url);
        share.setCategoryId(Integer.parseInt(category));
        share.setTitle(title);
        share.setDescription(description);
        share.setShareStatusId(LuppeItConstants.SHARE_STATUS_ACTIVE);
        User user = (User) Cache.get("currentUser");
        share.setUserId(user.getUserId());
        Resource resource = Resource.find("resourceName = ?", resourceUrl).first();
        share.setResourceId(resource.getResourceId());
        share.setViewCount(0);
        share.setLuppeCount(0);
        share.setDigCount(0);
        share.setLastModifiedDate(Calendar.getInstance().getTime());

        share.save();

        renderTemplate(NavigationConstants.submitLinkCompletePage, arguments, share);
    }*/


}

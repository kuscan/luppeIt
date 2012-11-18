package utils;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import play.libs.Mail;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: faruk
 * Date: 11/18/12
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class MailSender {

    public static void sendEmail(String subject, String htmlContent, String simpleContent, String mailTo) throws EmailException, MalformedURLException {

        HtmlEmail email = new HtmlEmail();
        email.addTo(mailTo);
        email.setFrom("faruk.kuscan@gmail.com", "LuppeIt");
        email.setSubject(subject);

        // embed the image and get the content id
        //URL url = new URL("http://www.zenexity.fr/wp-content/themes/images/logo.png");
        //String cid = email.embed(url, "Zenexity logo");
        // set the html message
        email.setHtmlMsg(htmlContent);
        // set the alternative message
        email.setTextMsg(simpleContent);

        Mail.send(email);




    }

}

package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

import javax.mail.search.StringTerm;

public class Application extends Controller {

    public static void index() {
        String text1 = "Text 1";
        String text2 = "Text 2";
        String text3 = "Text 3";
        render(text1, text2, text3);
    }

}
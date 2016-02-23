package pages

import geb.Page

/**
 * Created by kirankumar on 22/02/16.
 */
class EltropyTestPage extends Page{

    static url = "http://test.eltropy.org"
    static at = {title =~ "Eltropy Home"}
    /*static content = {

        EltropyTestLinkText {$(id: "nav-logo")}
        SignIn {$(class: "nav-signin-button")}
        GoogleButton {$("button", text:"Google")}
    }
*/
}

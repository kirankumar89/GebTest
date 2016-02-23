import geb.spock.GebReportingSpec
import org.openqa.selenium.Keys
import pages.EltropyTestPage


/**
 * Created by kirankumar on 22/02/16.
 */

class EltropySpec extends GebReportingSpec{


    def "Verify if Site Launched, Verify Title, Sign In button, UG course leads to SignIn "() {

        given:
        to EltropyTestPage

        driver.manage().window().maximize()

        when: "when in Home Page click on Heading Eltropy Test"
        $(id: "nav-logo").isDisplayed()
        $(id: "nav-logo").click()

        then: "On click it still leads to the same home page"
        assert title =~ "Eltropy Home"

        when: "In Home Page click on the course"
        $(id: "topic_403087").click()

        then: "On click of course leads to Sign In page, then get back to home"
        assert waitFor (10, 0.1){$(class: "btn eltropy-social-text eltropy-google-text").isDisplayed()}

        $(id: "nav-logo").click()
        assert waitFor (10, 0.1){$(id: "topic_403087").isDisplayed()}

        when: "when in Home Page click on Sign In button"
        $(class: "nav-signin-button").isDisplayed()
        $(class: "nav-signin-button").click()

        then: "A pop up modal with ways to login must be visible, verify and click close"
        waitFor (10, 0.1){$(class: "btn eltropy-social-text eltropy-google-text").isDisplayed()}
        $(class: "close").click()
        assert waitFor (10, 0.1){$(id: "topic_403087").isDisplayed()}

    }

  def "Google login navigation"() {

      when: "when in Home Page click on Sign In button"
      $(class: "nav-signin-button").isDisplayed()
      $(class: "nav-signin-button").click()
      waitFor (6, 0.1){$(class: "btn eltropy-social-text eltropy-google-text").isDisplayed()}

      then: "Click on Google button and verify if Google sign in page appears, go back to home page"
      $(class: "btn eltropy-social-text eltropy-google-text").click()
      assert waitFor (10, 0.1){$(class: "logo logo-w").isDisplayed()}
      driver.navigate().back()
      //assert waitFor (10, 0.1){title =~ "Eltropy Home"}
      assert waitFor (10, 0.1){$(id: "topic_403087").isDisplayed()}


  }

    def "LinkedIn login navigation"() {

        when: "when in Home Page click on Sign In button"
        $(class: "nav-signin-button").isDisplayed()
        $(class: "nav-signin-button").click()
        waitFor (10, 0.1){$(class: "btn eltropy-social-text eltropy-google-text").isDisplayed()}

        then: "Click on LinkedIn button and verify if LainkedIn sign in page appears, go back to home page"
        $(class: "btn eltropy-social-text eltropy-linkedin-text").click()
        assert waitFor (10, 0.1){$(class: "logo").isDisplayed()}
        driver.navigate().back()
        //assert waitFor (10, 0.1){title =~ "Eltropy Home"}
        assert waitFor (10, 0.1){$(id: "topic_403087").isDisplayed()}


    }

    def "SalesForce login navigation"() {

        when: "when in Home Page click on Sign In button"
        $(class: "nav-signin-button").isDisplayed()
        $(class: "nav-signin-button").click()
        waitFor (10, 0.1){$(class: "btn eltropy-social-text eltropy-google-text").isDisplayed()}

        then: "Click on SalesForce button and verify if SalesForce sign in page appears, go back to home page"
        $(text: "Salesforce").click()
        assert waitFor (10, 0.1){$(class: "input r4 wide mb16 mt8 username").isDisplayed()}
        driver.navigate().back()
        //assert waitFor (10, 0.1){title =~ "Eltropy Home"}
        assert waitFor (10, 0.1){$(id: "topic_403087").isDisplayed()}


    }

    def "Sign up modal navigation"() {

        when: "when in Home Page click on Sign In button"
        $(class: "nav-signin-button").isDisplayed()
        $(class: "nav-signin-button").click()
        waitFor (10, 0.1){$(class: "btn eltropy-social-text eltropy-google-text").isDisplayed()}

        then: "Click on LinkedIn button and verify if Sign up page appears, go back to home page"
        $(class: "email-choice-button email-ch-signup").click()
        assert waitFor (10, 0.1){$(id: "eltropy-signup-btn").isDisplayed()}
        $(text: "Back").click()
        waitFor (10, 0.1){$(class: "btn eltropy-social-text eltropy-google-text").isDisplayed()}
        //$(class: "close").click()
        //assert waitFor (10, 0.1){$(id: "topic_403087").isDisplayed()}


    }


    def "Forgot Password navigation"() {

        when: "when in Sign In Modal click Sign In button, verify if sign in as registered user is seen"
        //$(text: "Sign in").isDisplayed()
        waitFor (6, 0.1){$(text: "Sign in").click()}
        waitFor (6, 0.1){$(id: "eltropy-forgot-passwd-btn").isDisplayed()}

        then: "Click on Forgot Password button and verify if forgot password page is seen"
        $(id: "eltropy-forgot-passwd-btn").click()
        assert waitFor (10, 0.1){$(text: "Forgot your Password?").isDisplayed()}
        //go "http://test.eltropy.org"
        driver.navigate().back()
        //assert waitFor (10, 0.1){title =~ "Eltropy Home"}
        assert waitFor (10, 0.1){$(id: "topic_403087").isDisplayed()}


    }

   def "Sign In as a Registered User"(){

       when: "when in Home Page click on Sign In button"
       $(class: "nav-signin-button").isDisplayed()
       $(class: "nav-signin-button").click()
       waitFor (10, 0.1){$(class: "btn eltropy-social-text eltropy-google-text").isDisplayed()}

       then: "Click Sign In button, verify if Sign In modal appears"
       $(class: "email-choice-button email-ch-signin").click()
       assert waitFor (10, 0.1){$(id: "signin-email").isDisplayed()}

       when: "In sign in modal enter Incorrect credentials "
       $(id: "signin-email").value("kiran.kumar.sdet@gmail.com1234")
       $(id: "signin-password").value("test123456")
       $(id: "eltropy-login-btn").click()

       then: "verify if error message has appeared"
       assert waitFor (10, 0.1){$(class: "error-common-message common").isDisplayed()}
       driver.get("http://test.eltropy.org/")

       when: "Now click on Sign in again and use Valid username and password to proceed"
       $(class: "nav-signin-button").click()
       waitFor (10, 0.1){$(class: "btn eltropy-social-text eltropy-google-text").isDisplayed()}
       $(class: "email-choice-button email-ch-signin").click()
       assert waitFor (10, 0.1){$(id: "signin-email").isDisplayed()}
       $(id: "signin-email").value("kiran.kumar.sdet@gmail.com")
       $(id: "signin-password").value("test123456")
       $(id: "eltropy-login-btn").click()

       then: "Verify If user dashboard is visible"
       assert waitFor (20, 0.1){$(id: "nav-dashboard-icon").isDisplayed()}


   }

  def "Validate all Tabs in the Dashboard"(){

      when: "when in dashboard,"

      then: "validate if the page is in Modules Tab by Default"
      assert waitFor (30, 0.1){$(name: "q").isDisplayed()}

      when: "when in dashboard, click on Submissions icon on left"
      $(id: "submissionsTab").click()

      then: "validate if the page is showing Submissions"
      assert waitFor (10, 0.1){$(class: "submissions-title pull-left").isDisplayed()}


      when: "when in dashboard, click on activity icon on left"
      $(id: "activityTab").click()

      then: "validate if the page is showing Activity"
      assert waitFor (10, 0.1){$(class: "activity-search-wrapper").isDisplayed()}

      when: "when in dashboard, click on Email campaign icon on left"
      $(id: "emailCampaignTab").click()

      then: "validate if the page is showing Email Campaign"
      assert waitFor (10, 0.1){$(class: "ladda-button campaign-launch-btn ").isDisplayed()}

      when: "From the dashboard, click on Profile dropdown and goto profile page"
      $(class: "dropdown-toggle").click()
      $(id: "nav-account-button").click()

      then: "wait and validate if profile page is visible"
      assert waitFor (10, 0.1){$(name: "userName").isDisplayed()}
      driver.navigate().back()
      waitFor (30, 0.1){$(id: "modulesTab").click()}
      assert waitFor (10, 0.1){$(name: "q").isDisplayed()}
  }


  def "Modules: Search validations"(){

      when: "In modules tab enter a data which does not return a result"
      $(name: "q").value("Sometext")

      then: "validate that the number of results returned is 0"
      assert $("h3").text().contains("0 Result")

      when: "Enter a valid data"
      $(name: "q").value("test")

      then: "Validate that the number is not 0"
      assert $("h3").text() != "0 Result"

  }


   def "Topic validation"(){



   }

    /* def "Verify If inside Login Page and enter credentials and login"(){

       when: "Verify If Login Page is loaded"
       $(id:"signin-email").isDisplayed()

       then: "Enter credentials and Click Login"
       $(id:"signin-email").value("kiran.kumar.sdet@gmail.com")
       $(id:"signin-password").value("test123456")
       $(class:"btn btn-large btn-primary pull-right").click()


       //Google Login
      *//* $(name:"Email").value("kiran.kumar.sdet@gmail.com")
       $(name:"signIn").click()
       waitFor (3, 0.1){$(name:"Passwd")}
       $(name:"Passwd").value("Dhanapal123")
       //$(name:"Passwd") << Keys.chord(Keys.TAB,Keys.RETURN)
       $(id:"signIn").click()*//*

       waitFor (6, 0.1){$(id:"user-dropdown").isDisplayed()}
       $(id:"user-dropdown").isDisplayed()

   }

  */



}

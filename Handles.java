package day1;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Handles {
    @Test
    public void PlHandles() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://automatenow.io/sandbox-automation-testing-practice-website/");

        page.click("text=Popups");

        page.onDialog(dialog -> {
            assertEquals("confirm", dialog.type());
            String text= dialog.message();
            System.out.println(text);
           dialog.dismiss();
        });
        page.evaluate("confirm('Hi there, pal!')");
        page.click("text=Confirm Popup");

        String result1=page.textContent("#confirmResult");
        System.out.println(result1);


        page.close();
        playwright.close();
    }
}
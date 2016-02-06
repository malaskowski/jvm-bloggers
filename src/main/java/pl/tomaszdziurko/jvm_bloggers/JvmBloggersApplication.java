package pl.tomaszdziurko.jvm_bloggers;

import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;
import com.giffing.wicket.spring.boot.starter.context.WicketSpringBootApplication;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.devutils.stateless.StatelessChecker;
import org.apache.wicket.markup.html.WebPage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.tomaszdziurko.jvm_bloggers.view.HomePage;
import pl.tomaszdziurko.jvm_bloggers.view.admin.AdminHomePage;
import pl.tomaszdziurko.jvm_bloggers.view.login.LoginPage;
import pl.tomaszdziurko.jvm_bloggers.view.session.UserSession;

@SpringBootApplication
@EnableScheduling
@EnableEncryptableProperties
@WicketSpringBootApplication
public class JvmBloggersApplication extends WicketBootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(JvmBloggersApplication.class, args);
    }

    @Override
    protected void init() {
        super.init();
        getComponentPostOnBeforeRenderListeners().add(new StatelessChecker());
        mountPage("login", LoginPage.class);
        mountPage("admin", AdminHomePage.class);
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return UserSession.class;
    }
}

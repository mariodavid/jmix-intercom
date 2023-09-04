package io.jmix.intercom.view.main;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;
import elemental.json.Json;
import elemental.json.JsonObject;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.Views;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.intercom.entity.User;
import io.jmix.intercom.view.component.IntercomWidget;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
@ViewController("MainView")
@ViewDescriptor("main-view.xml")
public class MainView extends StandardMainView {


    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private Views views;

    @Subscribe
    public void onInit(final InitEvent event) {
        
        User user = (User) currentAuthentication.getUser();
        IntercomWidget intercom = new IntercomWidget(
                "s34blqos",
                user.getEmail(),
                user.getId().toString()
        );

        getContent().addToDrawer(intercom);
    }

}

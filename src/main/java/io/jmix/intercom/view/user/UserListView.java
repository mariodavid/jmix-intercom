package io.jmix.intercom.view.user;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import elemental.json.Json;
import elemental.json.JsonObject;
import io.jmix.intercom.entity.User;
import io.jmix.intercom.view.component.IntercomWidget;
import io.jmix.intercom.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

import java.util.Map;

@Route(value = "users", layout = MainView.class)
@ViewController("User.list")
@ViewDescriptor("user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
public class UserListView extends StandardListView<User> {
    @ViewComponent
    private HorizontalLayout wrapper;

    @Subscribe
    public void onInit(final InitEvent event) {
//        intercom.show();
//
//        JsonObject jsonMetadata = Json.createObject();
//        jsonMetadata.put("Visit Type", "Regular Checkup");
//        jsonMetadata.put("Pet Name", "Pikachu");
//
//        intercom.trackEvent("treatment-started", jsonMetadata);
    }


}

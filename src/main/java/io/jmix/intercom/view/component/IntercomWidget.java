package io.jmix.intercom.view.component;
import com.vaadin.flow.component.JsonSerializable;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import elemental.json.JsonValue;

import java.io.Serializable;

@Tag("intercom-widget")
@JsModule("./src/intercom-widget.ts")
public class IntercomWidget extends LitTemplate {

    public IntercomWidget(String appId, String email, String userId) {
        getElement().setProperty("appId", appId);
        getElement().setProperty("email", email);
        getElement().setProperty("userId", userId);
    }

    public void show() {
        getElement().callJsFunction("show");
    }

    public void shutdown() {
        getElement().callJsFunction("shutdown");
    }

    public void update(Serializable attributes) {
        getElement().callJsFunction("update", attributes);
    }

    public void hide() {
        getElement().callJsFunction("hide");
    }

    public void showMessages() {
        getElement().callJsFunction("showMessages");
    }

    public void showNewMessage(String preFilledContent) {
        getElement().callJsFunction("showNewMessage", preFilledContent);
    }

    public void trackEvent(String eventName, JsonValue metadata) {
        getElement().callJsFunction("trackEvent", eventName, metadata);
    }
}

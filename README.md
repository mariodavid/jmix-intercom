# Jmix Intercom

This addon provides integration with [Intercom](https://www.intercom.com/) customer messaging platform.

It uses Jmix 2.x Flow UI, which is based on Vaadin 24.

### Installation

The first step is to put the boostrap code for the intercom widget into the `index.html` file of your application (`frontend/index.html`):

```html
<html lang="en">
<head>
  <script type="text/javascript">
    jmixBeforeUnloadListener = (event) => {
      event.preventDefault();
      return (event.returnValue = "");
    };

    //Set your APP_ID
    var APP_ID = "<<YOUR_APP_ID>>";

    (function(){var w=window;var ic=w.Intercom;if(typeof ic==="function"){ic('reattach_activator');ic('update',w.intercomSettings);}else{var d=document;var i=function(){i.c(arguments);};i.q=[];i.c=function(args){i.q.push(args);};w.Intercom=i;var l=function(){var s=d.createElement('script');s.type='text/javascript';s.async=true;s.src='https://widget.intercom.io/widget/' + APP_ID;var x=d.getElementsByTagName('script')[0];x.parentNode.insertBefore(s, x);};if(document.readyState==='complete'){l();}else if(w.attachEvent){w.attachEvent('onload',l);}else{w.addEventListener('load',l,false);}}})();
  </script>
```


### Intercom Widget

Second step is to boot up the intercom widget. For this in this example, there is a custom LitElement, that allows to do that.

#### TS code: `frontend/src/intercom-widget.ts`

```typescript
import { LitElement } from 'lit';
import { property } from 'lit/decorators';

declare global {
    interface Window {
        Intercom: any;
    }
}

class IntercomWidget extends LitElement {

    @property({ type: String }) appId = "";
    @property({ type: String }) email = "";
    @property({ type: String }) userId = "";


    connectedCallback() {
        super.connectedCallback();
        if (this.appId) {
            window.Intercom('boot', {
                app_id: this.appId,
                email: this.email,
                user_id: this.userId
            });
        }
    }
    render() {
        return null;
    }

    show() {
        window.Intercom('show');
    }

    shutdown() {
        window.Intercom('shutdown');
    }

    update(attributes: object) {
        window.Intercom('update', attributes);
    }

    hide() {
        window.Intercom('hide');
    }

    showMessages() {
        window.Intercom('showMessages');
    }

    showNewMessage(preFilledContent?: string) {
        window.Intercom('showNewMessage', preFilledContent);
    }

    trackEvent(eventName: string, metadata: object) {
        window.Intercom('trackEvent', eventName, metadata);
    }
}

customElements.define('intercom-widget', IntercomWidget);
```

#### Java Code

```java
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
```


### Usage

Now, that all the setup is done, you can use the Intercom widget in your application.

```java

@Route("")
@ViewController("MainView")
@ViewDescriptor("main-view.xml")
public class MainView extends StandardMainView {


    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onInit(final InitEvent event) {
        
        User user = (User) currentAuthentication.getUser();
        IntercomWidget intercom = new IntercomWidget(
                "<<YOUR_APP_ID>>",
                user.getEmail(),
                user.getId().toString()
        );

        getContent().addToDrawer(intercom);
    }

}
```

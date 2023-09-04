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

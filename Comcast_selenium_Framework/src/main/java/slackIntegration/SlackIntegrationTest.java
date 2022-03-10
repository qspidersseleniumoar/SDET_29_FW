package slackIntegration;


import java.io.IOException;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

public class SlackIntegrationTest {
    //https://hooks.slack.com/services/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"

		private static String urlSlackWebHook = "https://hooks.slack.com/services/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";
		private static String channelName = "testing";
		private static String botUserOAuthAccessToken = "xoxb-3236780453760-3236818593392-RVbojBf887sIWGv6aDnx4kET";
		
		
			public void sendTestExecutionStatusToSlack(String message) throws Exception {
				try {
				StringBuilder messageBuider = new StringBuilder();
				messageBuider.append(message);
				Payload payload = Payload.builder().channel(channelName).text(messageBuider.toString()).build();

				WebhookResponse webhookResponse = Slack.getInstance().send(urlSlackWebHook, payload);
				webhookResponse.getMessage();
				} catch (IOException e) {
				System.out.println("Unexpected Error! WebHook:" + urlSlackWebHook);
				}
				}		
}

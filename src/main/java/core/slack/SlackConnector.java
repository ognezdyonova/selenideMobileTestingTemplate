package core.slack;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.methods.response.conversations.ConversationsListResponse;
import com.slack.api.methods.response.files.FilesUploadResponse;
import com.slack.api.model.Conversation;
import core.config.Prop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SlackConnector {
    public static String botToken;
    private static List<Conversation> conversationsStore;
    private static MethodsClient client;

//    public static void main(String[] args) throws Exception {
//        SlackConnector slack = new SlackConnector();
//        String chanelId = slack.fetchConversations().filterConversation(Configurations.getConfigParam("slack_chanel_name")).getId();
//        System.out.println(chanelId);
//        for (int i = 0; i < 2; i++) {
//            slack.sendMessage(Prop.getField("slack.channel.name"), "Test message  " + i);
//        }
//    }

    public SlackConnector() {
        client = Slack.getInstance().methods();
        botToken = Prop.getField("slack.bot.token");
    }

    /**
     * Fetch conversations using the conversations.list method
     *
     * @return
     */
    public SlackConnector fetchConversations() {
        Logger logger = LoggerFactory.getLogger("my-awesome-slack-app");
        try {
            ConversationsListResponse result = client.conversationsList(r -> r.token(botToken));
            conversationsStore = result.getChannels();
        } catch (IOException | SlackApiException e) {
            logger.error("error: {}", e.getMessage(), e);
        }
        return this;
    }

    public Conversation filterConversation(String name) {
        return conversationsStore.stream()
                .filter(conversation -> conversation.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Conversation " + name + " does not found!"));
    }

    public void sendMessage(String conversationId, String message) {
        Logger logger = LoggerFactory.getLogger("my-awesome-slack-app");
        try {
            ChatPostMessageResponse result = client.chatPostMessage(r -> r
                    .token(botToken)
                    .channel(conversationId)
                    .text(message));
        } catch (IOException | SlackApiException e) {
            logger.error("error: {}", e.getMessage(), e);
        }
    }

    public void sendFile(String conversationId, String title, String file){
        Logger logger = LoggerFactory.getLogger("my-awesome-slack-app");
        try {
            List channels = new ArrayList();
            channels.add(conversationId);
            FilesUploadResponse result = client.filesUpload(r -> {

                r.token(botToken)
                        .channels(channels)
                        .initialComment("Here's my file :smile:")
                        .title(title)
                        .file(new File(file));
                return r;
            });
            System.out.println(result);
        } catch (IOException | SlackApiException e) {
            logger.error("error: {}", e.getMessage(), e);
        }
    }


}

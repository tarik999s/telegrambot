package event.manager.telegram.bot.openai;

import lombok.Builder;
import event.manager.telegram.bot.openai.api.Message;

import java.util.List;

@Builder
public record ChatHistory(
        List<Message> chatMessages
) {
}

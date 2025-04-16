package event.manager.telegram.bot.command.handler;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import event.manager.telegram.bot.command.TelegramCommandHandler;
import event.manager.telegram.bot.command.TelegramCommands;
import event.manager.telegram.bot.openai.ChatGptHistoryService;

@Component
@AllArgsConstructor
public class ClearChatHistoryCommandHandler implements TelegramCommandHandler {

    private final ChatGptHistoryService chatGptHistoryService;

    private final String CLEAR_HISTORY_MESSAGE = "Ваша історія була очищена";

    @Override
    public BotApiMethod<?> processCommand(Message message) {
        chatGptHistoryService.clearHistory(message.getChatId());
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text(CLEAR_HISTORY_MESSAGE)
                .build();
    }

    @Override
    public TelegramCommands getSupportedCommand() {
        return TelegramCommands.CLEAR_COMMAND;
    }
}

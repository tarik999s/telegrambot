package event.manager.telegram.bot.command.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import event.manager.telegram.bot.command.TelegramCommandHandler;
import event.manager.telegram.bot.command.TelegramCommands;

@Component
public class StartCommandHandler implements TelegramCommandHandler {

    private final String HELLO_MESSAGE = """
            Привіт %s,
            Цим ботом ти можешь користуватись для розмови із GPT
            Кожне повідомлення запам'ятовується для контексту
            Очистіти контекст можна за допомогою команди /clear
            """;

    @Override
    public BotApiMethod<?> processCommand(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text(HELLO_MESSAGE.formatted(
                        message.getChat().getFirstName()
                ))
                .build();
    }

    @Override
    public TelegramCommands getSupportedCommand() {
        return TelegramCommands.START_COMMAND;
    }
}

package event.manager.telegram.bot.telegram.message;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import event.manager.telegram.bot.openai.ChatGptService;
import event.manager.telegram.bot.openai.TranscribeVoiceToTextService;
import event.manager.telegram.bot.telegram.TelegramFileService;

@Service
@AllArgsConstructor
public class TelegramVoiceHandler {

    private final ChatGptService gptService;
    private final TelegramFileService telegramFileService;
    private final TranscribeVoiceToTextService transcribeVoiceToTextService;

    public SendMessage processVoice(Message message) {
        var chatId = message.getChatId();
        var voice = message.getVoice();

        var fileId = voice.getFileId();
        var file = telegramFileService.getFile(fileId);
        var text = transcribeVoiceToTextService.transcribe(file);

        var gptGeneratedText = gptService.getResponseChatForUser(chatId, text);
        return new SendMessage(chatId.toString(), gptGeneratedText);
    }
}

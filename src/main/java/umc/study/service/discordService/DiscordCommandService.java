package umc.study.service.discordService;

import jakarta.annotation.PostConstruct;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.DiscordHandler;
import umc.study.apiPayload.exception.handler.TempHandler;

@Service
public class DiscordCommandService {

    @Value("${webhook.discord.url}")
    private String webhookUrl;
    @PostConstruct
    public void init() {
        System.out.println("🔧 Loaded Discord Webhook URL: " + webhookUrl);
    }


    public void sendAlert(String content) {
        WebClient.create()
                .post()
                .uri(webhookUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("content", content))
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe(); // 비동기
    }

    public void CheckContent(String content){
        if (content != null)
            throw new DiscordHandler(ErrorStatus.DISCORD_EXCEPTION);
    }
}

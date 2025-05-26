package umc.study.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DiscordErrorResponse {
    private final String url;
    private final String method;
    private final String timestamp;
    private final String message;

    public String toDiscordFormat() {
        return String.format("""
                🚨 *500 INTERNAL SERVER ERROR*
                - URL: `%s`
                - METHOD: `%s`
                - TIME: `%s`
                - MESSAGE: `%s`
                """, url, method, timestamp, message);
    }

    public String toSlackFormat() {
        return String.format("""
                *🚨 서버 에러 발생!*
                • URL: `%s`
                • Method: `%s`
                • Time: `%s`
                • Error: `%s`
                """, url, method, timestamp, message);
    }
}

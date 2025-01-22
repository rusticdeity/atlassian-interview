import org.example.ratelimiter.RateLimiter;
import org.example.ratelimiter.TokenBucket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTokenBucket {
  @Test
  void testTokenBucket() {
    TokenBucket tk = new TokenBucket(10, 5);
    assertEquals(10, tk.getAvailableTokens());
    assertEquals(10, tk.getTokensToRefill());
    assertEquals(5, tk.getRefillRateInSeconds());
  }

  @Test
  void testRefillWhenNoRequestsMade() {
    TokenBucket tk = new TokenBucket(10, 5);
    tk.refill();
    assertEquals(10, tk.getAvailableTokens());
  }

  @Test
  void testRefillWhenRequestsMade() {
    TokenBucket tk = new TokenBucket(10, 5);
    tk.isTokenAvailable();
    tk.isTokenAvailable();
    tk.isTokenAvailable();
    tk.refill();
    assertEquals(7, tk.getAvailableTokens());
  }

  @Test
  void testRefillWhenRefillRateElapsed() throws InterruptedException {
    TokenBucket tk = new TokenBucket(10, 1);
    tk.isTokenAvailable();
    tk.isTokenAvailable();
    Thread.sleep(1100);
    tk.refill();
    assertEquals(10, tk.getAvailableTokens());
  }

  @Test
  void testRefillDoesNotAddTokenWithInRateLimit() {
    TokenBucket tk = new TokenBucket(10, 1);
    tk.isTokenAvailable();
    tk.isTokenAvailable();
    tk.isTokenAvailable();
    tk.isTokenAvailable();
    tk.refill();
    assertEquals(6, tk.getAvailableTokens());
  }

  @Test
  void testIsTokenAvailable() {}
}

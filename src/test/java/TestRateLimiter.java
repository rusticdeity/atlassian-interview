import org.example.ratelimiter.RateLimiter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRateLimiter {
  @Test
  void testRateLimiter() {
    assertEquals(false, new RateLimiter().rateLimit(1));
  }
}

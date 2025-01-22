package org.example.ratelimiter;

import java.util.Map;

public class RateLimiter {

  private Map<Integer, TokenBucket> tokenBucketMap;

  public boolean rateLimit(int customerId) {
    TokenBucket customerTokenBucket =
        tokenBucketMap.getOrDefault(customerId, new TokenBucket(5, 1));
    return customerTokenBucket.isTokenAvailable();
  }
}

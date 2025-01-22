package org.example.ratelimiter;

public class TokenBucket {
  int availableTokens;
  int tokensToRefill;
  int refillRateInSeconds;
  long lastRequestTimeStampInMillis;

  public TokenBucket(int tokensToRefill, int refillRateInSeconds) {
    this.availableTokens = tokensToRefill;
    this.tokensToRefill = tokensToRefill;
    this.refillRateInSeconds = refillRateInSeconds;
    this.lastRequestTimeStampInMillis = System.currentTimeMillis();
  }

  public int getAvailableTokens() {
    return availableTokens;
  }

  public void setAvailableTokens(int availableTokens) {
    this.availableTokens = availableTokens;
  }

  public int getTokensToRefill() {
    return tokensToRefill;
  }

  public void setTokensToRefill(int tokensToRefill) {
    this.tokensToRefill = tokensToRefill;
  }

  public int getRefillRateInSeconds() {
    return refillRateInSeconds;
  }

  public void setRefillRateInSeconds(int refillRateInSeconds) {
    this.refillRateInSeconds = refillRateInSeconds;
  }

  public void refill() {
    long currentTime = System.currentTimeMillis();
    long elapsedTime = currentTime - lastRequestTimeStampInMillis;
    int tokensToAdd = (int) (elapsedTime / 1000) * tokensToRefill / refillRateInSeconds;
    availableTokens = Math.min(tokensToRefill, availableTokens + tokensToAdd);
  }

  public boolean isTokenAvailable() {
    refill();
    if (availableTokens > 0) {
      availableTokens--;
      lastRequestTimeStampInMillis = System.currentTimeMillis();
      return true;
    }
    return false;
  }
}

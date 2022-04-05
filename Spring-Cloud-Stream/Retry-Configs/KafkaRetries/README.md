
#Kafka Retry

This small demo showcases the following:

```yaml
maxAttempts: 5 
backOffInitialInterval: 2000
backOffMaxInterval: 10000
backoffMultiplier: 2.0
defaultRetryable: false
```
All properties are applied to the consumer binding:

- `maxAttempts` - initial + <i>n</i> retries
- `backOffInitialInterval` - initial backoff period
- `backOffMaxInterval` - maximum backoff period (useful to limit backoff growth)
- `backoffMultiplier` - 1.0=linear, 2.0=exponential, etc
- `defaultRetryable` - Whether exceptions thrown by the listener that are not listed in the retryableExceptions are retryable.


Given the above configurations, assuming a binding implementation failed on every try, the code would:
1. try then wait 2 seconds
2. try again then wait 4 seconds
3. try again then wait for 8 seconds
4. try again then wait for 10 seconds (because backOffMaxInterval is 10 seconds)
5. try again then throw the exception

# tweet-publisher-simulator
The `tweet-publisher-simulator` simulates a twitter feed that publishes 'processed' twitter messages containing 
only the following fields:

```scala
final case class Tweet(
                        id: String,
                        createdAt: Long,
                        text: String,
                        city: String,
                        lat: Double,
                        lng: Double,
                      )
```  

This message contains all the fields required for the swearing and cursing monitor. 
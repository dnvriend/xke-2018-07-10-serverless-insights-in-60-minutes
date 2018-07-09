package com.github.dnvriend.publisher.tweet

import java.util.Date

import org.scalacheck.Gen

import scala.compat.Platform
import scala.util.Random

final case class City(name: String, lat: Double, lng: Double)

object Noun {
  val words: List[String] = {
    val is = getClass.getClassLoader.getResourceAsStream("noun.txt")
    val xs = if(is == null) List.empty else scala.io.Source.fromInputStream(is).getLines().toList
    println(s"Loaded ${xs.size} of nouns")
    xs
  }

  def getRandom(): Noun = {
    Noun(words.randomElement())
  }
}
final case class Noun(value: String)

object Negative {
  val words: List[String] = {
    val is = getClass.getClassLoader.getResourceAsStream("negative.txt")
    val xs = scala.io.Source.fromInputStream(is).getLines().toList
    println(s"Loaded ${xs.size} of negative words")
    xs
  }
  def getRandom(): Negative = {
    Negative(words.randomElement())
  }
}
final case class Negative(value: String)

object Positive {
  val words: List[String] = {
    val is = getClass.getClassLoader.getResourceAsStream("positive.txt")
    val xs = scala.io.Source.fromInputStream(is).getLines().toList
    println(s"Loaded ${xs.size} of positive words")
    xs
  }
  def getRandom(): Positive = {
    Positive(words.randomElement())
  }
}
final case class Positive(value: String)

object Sentence {
  val words: List[String] = List(
    "Dual numbers where the underlying field is data types ... is that a thing? Pointers to literature would be very welcome ...",
    "Functional programming results in more maintainable codebases, less defects and higher performance. However, even in the face of  evidence, my profession continues to refer to it as an 'impractical overabstraction' and proceeds to shoot itself in the foot.",
    "DevOps is a way of thinking that builds optimized IT organizations. It’s not a method, framework or product. Learn about the six #DevOps principles and discover how you can achieve more speed, flexibility and simplicity within your IT organization.",
    "Did you know that with IntelliJ IDEA 2018.2 you can assign shortcuts to actions right from the Find Action pop-up? Just select the action, click Alt+Enter and choose a shortcut.",
    "New github4s release, now the cats-effect module is abstracted away from IO in favor of Sync and Async:",
    "Excellent thread to ponder over and debate.Oliver J.",
    "Sam & Max Hit the Road (1993)",
    "I’d totally go to such Kapibara Land if it materialized… :O",
    "Had a very unpleasant moment yesterday when a group of white men came over to me and my friend saying that we should take off our England flags since \"We're not English.\" Mind you, I was born here. It's my flag as much as it is yours.",
    "Monad is a _simple_ abstraction. The biggest problem is abusing “simple” to mean “already familiar.”",
    "Software development is a battle against complexity, and yet we invent complex abstractions that scare beginners (e.g. monads). Why? Because the cost of an abstraction is constant (you learn it once), while the cost of its absence (boilerplate) ranges from linear to exponential.",
    "OK, monads are simple, but monads + the underlying concepts add up to a fair bit of complexity. In order to define monads you need categories, functors, functor composition, the identity functor, monoids, and monoidal categories. These are all alien to the majority of developers.",
    "... and natural transformations. And realistically, to understand each of the concepts above a person needs a dozen of good examples. So the path to understanding monads is long and riddled with complexity. In retrospect it all seems painfully trivial, of course.",
    "Zero/constant cost of abstraction is a myth. Premature abstraction nowadays is a bigger evil than premature optimization. It almost definitely leads to an overdesigned mess. Monads are different of course. It is probably an abstraction with the most design efforts ever applied.",
    "It’s OK to figure out murder mysteries, but you shouldn’t need to figure out code. You should be able to read it.” – Steve McConnell",
    "When I was ~7 years old my dad forcefully enrolled me into ice hockey. I hated it so much I skated into the middle of the rink and laid down in protest. They had to drag me off the ice. I never played ice hockey again.",
    "I want a badge on software “made by middle-aged people with kids who’ve been around the block a few dozen times”",
    "For once, Im 100% qualified to talk about current news #ThaiCaveRescue\nHi I’m Puff & I’m a cave/cavern/tech/Nitrox/tri mix/wreck cert’d diver. Summer 01, I worked at a dive equipment manufacturer and was part of the only team in all central FL\nBuckle up, we’re talking rescue 1/X",
    "Hot take time. Abstract algebra and recursion schemes are useful and fun to idly mull over, but not necessary or sufficient for the study of computer science, let alone programming.",
    "The only way to resolve the UK Conservative party position on Brexit now is by Battle Royale. ",
    "The @acloudguru 1 year, all access subscription is probably the best $250 I have spent in a long time... maybe ever. The direct correlation to the advancement of my career is amazing. #notasponsor Check it out here:",
    "Dear #LambdaDays community, meet our first programme committee members http://ow.ly/WjYn30kPKLC  Excited to get their feedback on your work? Submit your proposal now!",
    "1) Modern Europe – liberal, democratic Europe – is the United States’ creation. This story was once known to every American, but as the generation responsible for this achievement dies, so too has the knowledge ceased to be passed down casually, within families.",
    "Every young man that emerges from that cave alive will be a testament to the indomitable human spirit.\nThe ability to overcome the more extreme of circumstances\nThe will to survive",
    "The API should be stable and 1.0.0 will be soon if there are no showstoppers.\n\nWhat does this let you do? Well, imagine this working out of the box, plus a new non-shapeless way to derive your typeclasses...",
    "Super excited to be accepting  this pack of stickers from @Hungai  on behalf of @lightbend. The pack includes around 125 mixed stickers (Lightbend, Lagom,Play Framework,Akka,Scala and Cats). @LagosScala is really grateful!!!",
    "Whoop...",
    "Had some fun down in the @NASA TV studio this morning recording a special message for the @MLB @AllStarGame!",
    "Get Programming with Go is nearing the finish line! Thanks to @CorbinCollins for the copyedit, @chaupt for the tech review, and @ErickZelaya for revised and new gopher illustrations. Get it from @ManningBooks at https://yng.mn/2qnYHha  \n#golang #programming #code",
    "Y’all got me watching The Last Jedi because nothing says Fourth of July 2018 like the slow, overwhelming encroachment of authoritarianism and a bunch of dudes who don’t get the underlying message.",
    "We are now at the \"wake up to hundreds of mentions as sub-threads devolve into self-sustaining arguments about Infinity War and The Last Jedi\" phase of this tweet's lifecycle.",
    "Like imagine if in Rocky Balboa he didn’t make a comeback and had no optimism and died alone. That’s The Last Jedi’s take on Luke.",
    "Watched Solo for the 2nd time & took my parents who both really enjoyed it. And i actually do have much more fun with this than the last Jedi. It feels like Star wars and is very nostalgic which to me is massively important",
    "This is not the point of The Last Jedi: “Let the past die, kill it if you have to.” \n\nThis is: “We are what they grow beyond.”",
    "Did you know? Benicio Del Toro suggested his final line in The Last Jedi be “maybe” ",
    "Every time I see someone complain about lack of realism in The Last Jedi I just presume they have never seen any other Star Wars film.",
    "do you ever think about the fact that we all heard kylo’s iconic “let the past die” line in the last jedi trailer as a voiceover and little did we know in the movie he’d be saying that shirtless in high waisted pants on forcetime with rey because i do a lot",
    "Hi, I hope you liked my movie, The Last Jedi.",
    "Oh Really? You hate The Last Jedi? ",
    "We also need a remake of The Last Jedi from the director of The Greasy Strangler.",
    "Star Wars fan gets to see Last Jedi before he dies ",
    "Been doing a deep dive into the reasons fanboys say they hate Rose Tico in The Last Jedi. It’s exhausting. These dudes swear up and down it’s not because of her gender or race except their complaints boil down to an endless parade of “I’m not sexist/racist but…” statements.",
    "Swimming underwater around the West Pier columns this morning, occasional rippling glimpses of a Daliesque world above.",
    "Synthesizing realistic high-resolution images with Glow, a new reversible generative model",
    "Healthy lifestyle: 5 keys to a longer life - Harvard Health Blog - Harvard Health Publishing",
    "Brb, making sure everything in the house is POSIX compliant",
    "Don't get under-sores from all these underscores... Just \"__init__\" your day with a steaming hot mug of pure Python (or coffee, or tea) and this unique mug for Python developers. This mug and many more unique swags for software developers are available at http://nerdlettering.com ",
    "The Speed Update, which enables page speed in mobile search ranking, is now rolling out for all users!",
    "VICTORY!",
    "Wouldn’t it be nice with an @deterministic method annotation in the Java core—to be used to instruct the vm to do common subexpression elimination where input does not vary?",
    "Yeah, I’m thinking about those other cases :)",
    "The idea is entirely sensible.  The problem is: how do we trust the annotation?  I would prefer to have a set of linguistic rules that can check purity, but this requires \"purity all the way down\", and given that everything is mutable in the silicon...",
    "We need to make racism expensive\nWe need to make racism dangerous\nWe need to make racism have social consequences \nWe need to make racism costly b/c we're not gonna change racists into non-racists w/\"reasoned dialogue\" but we can make them think twice about acting on their racism",
    "Another weekend being eaten alive in panic over your Russian-backed (so ridiculous) \"victory\"?... \"a fraud against our Nation\", indeed. ",
    "What I did I do this past week? Cancer therapy research with AngieX…investigating how to halt the formation of a tumor’s blood supply. Doing our part to help stop the Emperor of All Maladies. Love my work up here on ",
    "Every young man that emerges from that cave alive will be a testament to the indomitable human spirit.\nThe ability to overcome the more extreme of circumstances\nThe will to survive",
    "Luke and Leia (with the aid of a lightsaber) help Kneesaa and Wicket stop a rampaging Gorax by resetting the Ewok's traps in #ForcesOfDestiny: \"Traps and Tribulations.\"",
    "good morning to everyone except off-brand Donald Trump, Boris Johnson",
    "Hello people, my fiancé cat gave birth again the other day and she has some kittens to give away because there's too many to handle. Looking for a kind and loving soul to take care of these sweethearts. It's sad that she have to let them ",
    "No kittens were squished to death during this photoshoot ",
    "Does anyone want 5 abandoned kittens for free?? they’re real little but need a home and all shelters are full or will kill them;(",
  )

  def getSentence(noun: Noun, positive: Positive, negative: Negative, random: Random): Sentence = {
    if (random.nextBoolean()) Sentence(words.randomElement()) else {
      val sentiment = if (random.nextBoolean()) positive.value else negative.value
      Sentence(s"The ${noun.value} is $sentiment")
    }
  }
}

final case class Sentence(value: String)

/**
  * TweetGenerator generates a tweet with random content. The tweet-data is designed to test the analytical capabilities
  * of the swearing-and-cursing-monitor
  */
object TweetGenerator {
  val listOfCities: Seq[City] = Seq(
    City("amsterdam", 52.37403, 4.88969),
    City("almelo", 52.35667, 6.6625),
    City("almere", 52.37025, 5.21413),
    City("amstelveen", 52.30083, 4.86389),
    City("amersfoort", 52.155, 5.3875),
    City("apeldoorn", 52.21, 5.96944),
    City("born", 51.03167, 5.80972),
    City("eindhoven", 51.44083, 5.47778),
    City("rotterdam", 51.9225, 4.47917),
    City("den haag", 52.07667, 4.29861),
    City("utrecht", 52.09083, 5.12222),
    City("hilversum", 52.22333, 5.17639),
    City("maastricht", 50.84833, 5.68889),
    City("leeuwarden", 53.20139, 5.80859),
    City("hoorn", 52.6425, 5.05972),
    City("hoofddorp", 52.3025, 4.68889),
    City("hengelo", 52.26583, 6.79306),
    City("gouda", 52.01667, 4.70833),
    City("groningen", 53.21917, 6.56667),
    City("lelystad", 52.50833, 5.475),
    City("leiden", 52.15833, 4.49306),
    City("nijmegen", 51.8425, 5.85278),
    City("helmond", 51.48167, 5.66111),
    City("enschede", 52.21833, 6.89583),
    City("oss", 51.765, 5.51806),
    City("purmerend", 52.505, 4.95972),
    City("roosendaal", 51.53083, 4.46528),
    City("schiedam", 51.91917, 4.38889),
    City("spijkenisse", 51.845, 4.32917),
    City("tilburg", 51.55551, 5.0913),
    City("venlo", 51.37, 6.16806),
    City("zaandam", 52.43854, 4.82643),
    City("zaanstad", 52.45313, 4.81356),
    City("zoetermeer", 52.0575, 4.49306),
    City("zwolle", 52.5125, 6.09444)
  )

  def sentence(noun: Noun, positive: Positive, negative: Negative): Sentence = {
    val sentiment = if (Random.nextBoolean()) positive.value else negative.value
    Sentence(s"The ${noun.value} is $sentiment")
  }

   val genCity: Gen[City] = Gen.oneOf(listOfCities)

  def now(): Long = new Date().getTime

   val generateTweet: Gen[Tweet] = for {
    id <- Gen.uuid.map(_.toString)
    text <- Gen.const(Sentence.getSentence(Noun.getRandom(), Positive.getRandom(), Negative.getRandom(), Random).value)
    city <- genCity
  } yield Tweet(
    id,
    now(),
    text,
    city.name,
    city.lat,
    city.lng,
  )

  def iterFor[A](gen: Gen[A]): Iterator[A] = Stream.continually(gen.sample).collect { case Some(x) => x }.iterator

  def tweetIterator: Iterator[Tweet] = iterFor[Tweet](generateTweet)

  def tweetList(n: Int): List[Tweet] = {
    iterFor[Tweet](generateTweet).take(n).toList
  }
}

object TweetGeneratorTest extends App {
  val num = 500
  val start = Platform.currentTime
  println(s"Creating $num of tweets")
  TweetGenerator.tweetList(num).map(Tweet.analyzeTweet) foreach println
  println("Took: " + (Platform.currentTime - start))
}
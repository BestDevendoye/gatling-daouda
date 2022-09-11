package videogameDB
import  io.gatling.http.Predef._
import  io.gatling.core.Predef._

class AddresponseExtractBody  extends  Simulation{

  val httpconf = http.baseUrl("https://www.videogamedb.uk:443/api")
    .acceptHeader("application/json")

  val scn = scenario("GET SAMPLE VIDEO GAME")
    .exec(http("Get Specific video").get("/videogame/2").check(status.is(200)).check(jsonPath("$.name").is("Gran Turismo 3")))

    .exec(http("Get all game Video").get("/videogame").check(jsonPath("$[1].id").saveAs("gameid")))
    .exec {session => println(session); session}

    .exec(http("request specific video ").get("/videogame/#{gameid}").check(status.is(200)))


  setUp(scn.inject(atOnceUsers(1)).protocols(httpconf))
}

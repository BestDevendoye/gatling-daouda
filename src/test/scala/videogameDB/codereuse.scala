package videogameDB
import  io.gatling.http.Predef._
import  io.gatling.core.Predef._


class codereuse extends  Simulation{

  val httpconf = http.baseUrl("https://www.videogamedb.uk:443/api")
    .acceptHeader("application/json")

  def getallvideogames()  =
    repeat(3)
  {
    {
      exec(http("Get all games")
        .get("/videogame")
        .check(status.is(200)))
    }

  }

  def getspecificgames() =
    repeat (5)
  {
    {
      exec(http("Get specific games")
        .get("/videogame/3")
        .check(status.is(200)))
    }

  }


  val scn = scenario("Scenario reuse")
    .exec(getallvideogames())
    .pause(5)
    .exec(getspecificgames())
    .pause(3)

  setUp(scn.inject(atOnceUsers(1)).protocols(httpconf))
}

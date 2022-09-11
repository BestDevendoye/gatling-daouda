package videogameDB

import  io.gatling.core.Predef._
import  io.gatling.http.Predef._

class MyfirstTest  extends  Simulation{

  val httpconf = http.baseUrl("https://www.videogamedb.uk:443/api")
    .acceptHeader("application/json")

  val scn = scenario("video game")
    .exec(http("videogame")
      .get("/videogame"))




setUp(scn.inject(atOnceUsers(1)).protocols(httpconf))
}

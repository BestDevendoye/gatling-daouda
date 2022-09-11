package videogameDB
import  io.gatling.core.Predef._
import  io.gatling.http.Predef._
import io.gatling.core.Predef.Simulation

class AddPauseTime  extends  Simulation {

  val httpconf = http.baseUrl("https://www.videogamedb.uk:443/api")

  val scn = scenario("Best video game")
    .exec(http("Get videoGame 1st call").get("/videogame").check(status.is(200))).pause(5)

    .exec(http("Get specific game ").get("/videogame/2").check(status.in(200 to 210))).pause(3)

    .exec(http("Get videoGame 2sc  call").get("/videogame").check(status.not(404))).pause(6)


  setUp(scn.inject(atOnceUsers(2)).protocols(httpconf))

}

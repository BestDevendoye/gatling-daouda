package feeder
import  io.gatling.http.Predef._
import  io.gatling.core.Predef._

class commandline  extends  Simulation{


  val httpconf = http.baseUrl("https://www.videogamedb.uk:443/api")
    .acceptHeader("application/json")

  def getvideoall()= {
    exec(http("get video all")
      .get("/videogame")
    )
  }


  val scn= scenario("commande Line")
    .exec(getvideoall())
    .pause(5)


  setUp(scn.inject(nothingFor(5),rampUsers(10).during(10)).protocols(httpconf)).maxDuration(60)


}

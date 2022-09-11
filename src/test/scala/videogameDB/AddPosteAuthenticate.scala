package videogameDB
import  io.gatling.http.Predef._
import  io.gatling.core.Predef._

class AddPosteAuthenticate extends Simulation{

  val httpconf = http.baseUrl("https://www.videogamedb.uk:443/api")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  def authenticate()=
  {
    exec(http("authenticate")
    .post("/authenticate")
    .body(StringBody(string = "{\n  \"password\": \"admin\",\n  \"username\": \"admin\"\n}"))
    .check(jsonPath("$.token").saveAs("jsonkey")))
  }

  def Postgame() =
  {
    exec(http("create game")
     .post("/videogame")
      .header("Authorization", "Bearer #{jsonkey}")
    .body(StringBody(string = "{\n  \"category\": \"Platform\",\n  \"name\": \"Mario\",\n  \"rating\": \"Mature\",\n  \"releaseDate\": \"2012-05-04\",\n  \"reviewScore\": 85\n}"))
    .check(status.is(200)))
  }



  val scn  = scenario("Post creation")
    .exec(authenticate())
    .pause(5)
    .exec(Postgame())



  setUp(scn.inject(atOnceUsers(1)).protocols(httpconf))







}

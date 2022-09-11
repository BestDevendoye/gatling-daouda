package feeder
import  io.gatling.http.Predef._
import io.gatling.core.Predef._

class feedercsv extends  Simulation{

  val httpconf = http.baseUrl("https://www.videogamedb.uk:443/api")
    .acceptHeader(" application/json")
   val csvfeeder =  csv("/Users/daoud/Downloads/gatling-smit/gatling-maven/src/test/resources/data/freederfile.csv").circular

     def videogamecsv() =
  {
    repeat(10)
    {
      feed(csvfeeder)
        .exec(http("videogame specific csv w")
        .get("/videogame/#{gameid}")
        .check(jsonPath("$.name").is("#{gamename}")))
    }
  }

  val scn = scenario("GAME VIDEO")
           .exec(videogamecsv())


  setUp(scn.inject(atOnceUsers(1)).protocols(httpconf))

}

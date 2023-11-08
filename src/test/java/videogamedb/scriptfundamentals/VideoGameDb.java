package videogamedb.scriptfundamentals;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class VideoGameDb extends Simulation {

    // 1 Http Configuration
    private HttpProtocolBuilder httpprotocol=http
            .baseUrl("https://videogamedb.uk/api")
            .acceptHeader("application/json");

    // 2 Scenario Definition
    private ScenarioBuilder scn=scenario("Video game DB- section 5 code")
            .exec(http("Get all video games- 1st call")
                    .get("/videogame"))
            .pause(5)

            .exec(http("Get specific game")
                    .get("/videogame/1"))

            .pause(1,10)

            .exec(http("Get all video games- 2nd call")
                    .get("/videogame"))
            .pause(Duration.ofMillis(4000));

    // 3 Load Simulation
    {
        setUp(
                scn.injectOpen(atOnceUsers(5))
        ).protocols(httpprotocol);
    }




}

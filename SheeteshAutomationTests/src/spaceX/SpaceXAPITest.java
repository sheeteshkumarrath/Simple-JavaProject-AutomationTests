package spaceX;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class SpaceXAPITest {
	public static void main(String[] args) throws Exception {
		validateSpaceXdataGETResponse();
	}

	private static Response spaceXdataGETResponse() {
		RestAssured.baseURI = "https://api.spacexdata.com";
		RestAssured.basePath = "/v4/launches";

		Response res= RestAssured.when().
				get("/latest");
		System.out.println(res.asString());
		return res;
	}

	//@Test
	public static void validateSpaceXdataGETResponse() throws Exception{

		spaceXdataGETResponse().then().assertThat().statusCode(200).and().
		contentType(ContentType.JSON).and().
		body("fairings", nullValue()).and().
		body("links.patch.small", equalTo("https://imgur.com/o6zaoex.png")).and().
		body("links.patch.large", equalTo("https://imgur.com/klt5qq2.png")).and().
		body("links.reddit.campaign", equalTo("https://www.reddit.com/r/spacex/comments/nhztq5/crs22_launch_campaign_thread/")).and().
		body("links.reddit.launch", equalTo("https://www.reddit.com/r/spacex/comments/nqqojc/rspacex_crs22_launch_docking_discussion_updates/")).and().
		body("links.reddit.media", nullValue()).and().
		body("links.reddit.recovery", equalTo("https://www.reddit.com/r/spacex/comments/k2ts1q/rspacex_fleet_updates_discussion_thread/")).and().
		body("links.flickr.small", empty()).and().
		body("links.flickr.original", empty()).and().  
		body("links.presskit", nullValue()).and().
		body("links.webcast", equalTo("https://youtu.be/QXf9mRWbXDM")).and().
		body("links.youtube_id", equalTo("QXf9mRWbXDM")).and().
		body("links.article", nullValue()).and().
		body("links.wikipedia", equalTo("https://en.wikipedia.org/wiki/SpaceX_CRS-22")).and().
		body("static_fire_date_utc", nullValue()).and().
		body("static_fire_date_unix", nullValue()).and().
		body("tbd", is(false)).and().
		body("net", is(false)).and().
		body("window", is(0)).and().
		body("rocket", equalTo("5e9d0d95eda69973a809d1ec")).and().
		body("success", is(true)).and().
		body("details", equalTo("SpaceX's 22nd ISS resupply mission on behalf of NASA, this mission sends essential supplies to the International Space Station using the cargo variant of SpaceX's Dragon 2 spacecraft. The external payload for this mission is the first pair of ISS Roll Out Solar Arrays. Falcon 9 and Dragon launch from LC-39A, Kennedy Space Center and the booster is expected to land on an ASDS. The mission will be complete with splashdown and recovery of the capsule and down cargo.")).and().
		body("crew", empty()).and().
		body("ships[0]", equalTo("5ea6ed2f080df4000697c90b")).and().
		body("ships[1]", equalTo("608c1a06cf7f3d6152666ad4")).and().
		body("ships[2]", equalTo("5ea6ed30080df4000697c913")).and().
		body("capsules[0]", equalTo("60b803421f83cc1e59f1644d")).and().
		body("payloads[0]", equalTo("5fe3b642b3467846b324217b")).and().
		body("launchpad", equalTo("5e9e4502f509094188566f88")).and().
		body("auto_update", is(true)).and().
		body("launch_library_id", equalTo("89a150ea-6e4b-489f-853c-3603ae684611")).and().
		body("failures", empty()).and().
		body("flight_number",is(129)).and().
		body("name", equalTo("CRS-22 & IROSA")).and().
		body("date_utc", equalTo("2021-06-03T17:29:00.000Z")).and().
		body("date_unix",is(1622741340)).and().
		body("date_local", equalTo("2021-06-03T13:29:00-04:00")).and().
		body("date_precision", equalTo("hour")).and().
		body("upcoming", is(false)).and().
		body("cores[0].core", equalTo("60b800111f83cc1e59f16438")).and().
		body("cores[0].flight",is(1)).and().
		body("cores[0].gridfins", is(true)).and().
		body("cores[0].legs", is(true)).and().
		body("cores[0].reused", is(false)).and().
		body("cores[0].landing_attempt", is(true)).and().
		body("cores[0].landing_success", is(true)).and().
		body("cores[0].landing_type", equalTo("ASDS")).and().
		body("cores[0].landpad", equalTo("5e9e3032383ecb6bb234e7ca")).and().
		body("id", equalTo("5fe3af84b3467846b3242161"));
		System.out.println("******* All Response Body Assersions are PASSED ********");
		System.out.println("*************** Test Execution Completed ***************");
	}
}

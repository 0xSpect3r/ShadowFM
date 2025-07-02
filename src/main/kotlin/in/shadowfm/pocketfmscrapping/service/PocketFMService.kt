package `in`.shadowfm.pocketfmscrapping.service

import `in`.shadowfm.pocketfmscrapping.dto.FeedResult
import `in`.shadowfm.pocketfmscrapping.dto.FeedTab
import `in`.shadowfm.pocketfmscrapping.dto.ResponseWrapper
import `in`.shadowfm.pocketfmscrapping.dto.Show
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class PocketFMService {
    val baseUrl: String = "https://web.pocketfm.com/"

    fun getHeaders() : Headers {
        return Headers.Builder()
                //.add("access-token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJmMzgzNzdlNWY4ZWVlNWFjYmRjM2Y5YjY2NDA2MjIxYmE3YTk2ZGM5IiwiYWNjZXNzX3Rva2VuIjoiZTFhZDA4MDdjOGI5YTI2MGJmYmY5ZTAzZWMxNmY1ZjI5YWJkZjA5MSIsImRldmljZS1pZCI6ImZkNDRkY2M5NTUwYjZkNjEiLCJhdXRoX3Rva2VuIjoid2ViLWF1dGgiLCJsYXN0X2FjdGl2ZV9wbGF0Zm9ybSI6ImFuZHJvaWQiLCJsYXN0X2FjdGl2ZV9kZXZpY2VfYXBwX3ZlcnNpb25fY29kZSI6Ijc1MyIsImV4cCI6MTcwMTAwODM5Mn0.5f_T3gjLWx01UbR5Jiy3kp5lLLGFfofqRbu9sAUCU1M")
                //.add("device-id", "fd44dcc9550b6d61")
                .add("authority", "web.pocketfm.com")
                .add("accept", "application/json, text/plain, */*")
                .add("accept-language", "en-IN,en-US;q=0.9,en;q=0.8,hi-IN;q=0.7,hi;q=0.6,en-GB;q=0.5")
                .add("app-client", "consumer-web")
                .add("app-version", "180")
                .add("auth-token", "web-auth")
                .add("cache-control", "no-cache")
                .add("dnt", "1")
                .add("origin", "https://pocketfm.com")
                .add("pragma", "no-cache")
                .add("referer", "https://pocketfm.com/")
                .add("sec-ch-ua", "\"Google Chrome\";v=\"117\", \"Not;A=Brand\";v=\"8\", \"Chromium\";v=\"117\"")
                .add("sec-ch-ua-mobile", "?0")
                .add("sec-ch-ua-platform", "\"macOS\"")
                .add("sec-fetch-dest", "empty")
                .add("sec-fetch-mode", "cors")
                .add("sec-fetch-site", "same-site")
                .add("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36").build()
    }

    fun fetchFeedTabs() : List<FeedTab>? {
        val client = OkHttpClient()

        val request = Request.Builder()
                .url("https://web.pocketfm.com/v2/feed_api/get_personalised_feed_tabs?api_type=")
                .headers(getHeaders())
                .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            val typeRef: TypeReference<ResponseWrapper<List<FeedTab>>> = object : TypeReference<ResponseWrapper<List<FeedTab>>>() {}
            return ObjectMapper().readValue(response.body!!.string(), typeRef).result

        }
    }

    fun fetchShowsByTabs(feed: String) : List<FeedResult>? {
        val client = OkHttpClient()

        val request = Request.Builder()
                .url("https://web.pocketfm.com/v2/feed_api/get_feed_data?api_type="+feed+"&is_mobile=0")
                .headers(getHeaders())
                .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            val typeRef: TypeReference<ResponseWrapper<List<FeedResult>>> = object : TypeReference<ResponseWrapper<List<FeedResult>>>() {}
            return ObjectMapper().readValue(response.body!!.string(), typeRef).result
        }
    }

    fun fetchEpisodes(show: String, currPtr: Int) : List<Show>? {
        val client = OkHttpClient()

        val request = Request.Builder()
                .url("https://web.pocketfm.com/v2/content_api/show.get_details?show_id=$show&curr_ptr=$currPtr&info_level=max")
                .headers(getHeaders())
                .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            val typeRef: TypeReference<ResponseWrapper<List<Show>>> = object : TypeReference<ResponseWrapper<List<Show>>>() {}
            return ObjectMapper().readValue(response.body!!.string(), typeRef).result
        }
    }
}

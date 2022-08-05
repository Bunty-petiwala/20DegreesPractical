package app.com.tweentydegreespractical.data.remote.api

import app.com.tweentydegreespractical.data.remote.api.NewsService.Companion.NEWS_API_URL
import app.com.tweentydegreespractical.model.PostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service to fetch News posts using dummy end point [NEWS_API_URL].
 */
interface NewsService {

    @GET("everything")
    suspend fun getPosts(
      @Query("q")   query: String,
      @Query("from")   from: String,
      @Query("sortBy")   sortBy: String,
      @Query("apiKey")   apiKey: String,
    ): Response<PostResponse>

    companion object {
        const val NEWS_API_URL = "https://newsapi.org/v2/"
    }
}

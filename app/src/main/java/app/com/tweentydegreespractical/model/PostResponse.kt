package app.com.tweentydegreespractical.model

data class PostResponse(
    val articles: List<Post>,
    val status: String,
    val totalResults: Int
)
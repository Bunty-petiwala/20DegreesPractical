package app.com.tweentydegreespractical.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.com.tweentydegreespractical.model.Post.Companion.TABLE_NAME

/**
 * Data class for Database entity and Serialization.
 */
@Entity(tableName = TABLE_NAME)
data class Post(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    var title: String? = null,
    var author: String? = null,
    var description: String? = null,
    var urlToImage: String? = null
) {
    companion object {
        const val TABLE_NAME = "articles_posts"
    }
}

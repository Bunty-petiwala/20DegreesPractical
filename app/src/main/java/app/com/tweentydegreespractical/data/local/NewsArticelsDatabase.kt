

package app.com.tweentydegreespractical.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.com.tweentydegreespractical.data.local.dao.PostsDao
import app.com.tweentydegreespractical.model.Post

/**
 * Abstract News database.
 * It provides DAO [PostsDao] by using method [getPostsDao].
 */
@Database(
    entities = [Post::class],
    version = DatabaseMigrations.DB_VERSION
)
abstract class NewsArticelsDatabase : RoomDatabase() {

    /**
     * @return [PostsDao] News Posts Data Access Object.
     */
    abstract fun getPostsDao(): PostsDao

    companion object {
        const val DB_NAME = "news_database"

        @Volatile
        private var INSTANCE: NewsArticelsDatabase? = null

        fun getInstance(context: Context): NewsArticelsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsArticelsDatabase::class.java,
                    DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}

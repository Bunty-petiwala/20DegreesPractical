package app.com.tweentydegreespractical.data.repository

import androidx.annotation.MainThread
import app.com.tweentydegreespractical.data.local.dao.PostsDao
import app.com.tweentydegreespractical.data.remote.api.NewsService
import app.com.tweentydegreespractical.model.Post
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import retrofit2.Response
import javax.inject.Inject

interface PostRepository {
    fun getAllPosts(): Flow<Resource<List<Post>>>
    fun getPostById(postId: Int): Flow<Post>
}

@ExperimentalCoroutinesApi
class DefaultPostRepository @Inject constructor(
    private val postsDao: PostsDao,
    private val newsService: NewsService
) : PostRepository {

    /**
     * Fetched the posts from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    override fun getAllPosts(): Flow<Resource<List<Post>>> {
        return object : NetworkBoundRepository<List<Post>, List<Post>>() {

            override suspend fun saveRemoteData(response: List<Post>) = postsDao.addPosts(response)

            override fun fetchFromLocal(): Flow<List<Post>> = postsDao.getAllPosts()

            override suspend fun fetchFromRemote(): Response<List<Post>> {

                val respons = newsService.getPosts(
                    "tesla",
                    "2022-07-05",
                    "publishedAt",
                    "82178c6915fd4d8bbf046f2b367b6f2d"
                )

                val article: Response<List<Post>> = Response.success(respons.body()!!.articles)

                return article
            }
        }.asFlow()
    }

    /**
     * Retrieves a post with specified [postId].
     * @param postId Unique id of a [Post].
     * @return [Post] data fetched from the database.
     */
    @MainThread
    override fun getPostById(postId: Int): Flow<Post> =
        postsDao.getPostById(postId).distinctUntilChanged()
}

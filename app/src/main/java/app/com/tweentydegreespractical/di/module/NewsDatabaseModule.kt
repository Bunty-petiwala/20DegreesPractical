

package app.com.tweentydegreespractical.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import app.com.tweentydegreespractical.data.local.NewsArticelsDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NewsDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = NewsArticelsDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostsDao(database: NewsArticelsDatabase) = database.getPostsDao()
}

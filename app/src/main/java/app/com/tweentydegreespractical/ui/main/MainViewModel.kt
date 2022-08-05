

package app.com.tweentydegreespractical.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import app.com.tweentydegreespractical.data.repository.PostRepository
import app.com.tweentydegreespractical.model.Post
import app.com.tweentydegreespractical.model.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for [MainActivity]
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val postRepository: PostRepository) :
    ViewModel() {

    private val _posts: MutableStateFlow<State<List<Post>>> = MutableStateFlow(State.loading())

    val posts: StateFlow<State<List<Post>>> = _posts

    fun getPosts() {
        viewModelScope.launch {
            postRepository.getAllPosts()
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _posts.value = state }
        }
    }
}

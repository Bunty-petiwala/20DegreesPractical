

package app.com.tweentydegreespractical.ui.main.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import app.com.tweentydegreespractical.R
import app.com.tweentydegreespractical.databinding.ItemPostBinding
import app.com.tweentydegreespractical.model.Post
import app.com.tweentydegreespractical.ui.main.adapter.PostListAdapter

/**
 * [RecyclerView.ViewHolder] implementation to inflate View for RecyclerView.
 * See [PostListAdapter]]
 */
class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post, onItemClicked: (Post, ImageView) -> Unit) {
        binding.postTitle.text = post.title
        binding.postAuthor.text = post.author
        binding.imageView.load(post.urlToImage) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }

        binding.root.setOnClickListener {
            onItemClicked(post, binding.imageView)
        }
    }
}

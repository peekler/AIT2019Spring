package hu.ait.aitforum.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import hu.ait.aitforum.R
import hu.ait.aitforum.data.Post
import kotlinx.android.synthetic.main.row_post.view.*

class PostsAdapter(
    private val context: Context,
    private val uId: String) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    private var postsList = mutableListOf<Post>()
    private var postKeys = mutableListOf<String>()

    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_post, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount() =  postsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (uid, author, title, body, imgUrl) =
            postsList[holder.adapterPosition]

        holder.tvAuthor.text = author
        holder.tvTitle.text = title
        holder.tvBody.text = body

        setAnimation(holder.itemView, position)
    }

    fun addPost(post: Post, key: String) {
        postsList.add(post)
        postKeys.add(key)
        notifyDataSetChanged()
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context,
                android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAuthor: TextView = itemView.tvAuthor
        val tvTitle: TextView = itemView.tvTitle
        val tvBody: TextView = itemView.tvBody
    }
}
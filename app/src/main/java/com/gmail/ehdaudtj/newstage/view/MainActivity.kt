package com.gmail.ehdaudtj.newstage.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView

import com.gmail.ehdaudtj.newstage.model.Feed
import org.jetbrains.anko.*


class MainActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }
}

class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            padding = dip(22)
            backgroundColor = 0xf3f4f5.opaque
            verticalLayout {
                backgroundColor = Color.WHITE
                linearLayout {
                    leftPadding = dip(14)
                    verticalPadding = dip(7)
                    textView("HelloWorld")
                }
            }
        }
    }
}

class FeedAdapter(val feedList: List<Feed>) : RecyclerView.Adapter<FeedItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FeedItemViewHolder? {
        return FeedItemViewHolder(FeedItemUI().createView(AnkoContext.create(parent!!.context, parent)))
    }

    override fun onBindViewHolder(holder: FeedItemViewHolder?, position: Int) {
        val feed = feedList[position]
        holder!!.bind(feed)
    }

    override fun getItemCount(): Int {
        return feedList.size
    }
}

class FeedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.find(R.id.feed_title)

    fun bind(feed: Feed) {
        title.text = feed.title
    }
}

class FeedItemUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = dip(48))
                orientation = LinearLayout.HORIZONTAL
                textView {
                    id = R.id.feed_title
                    textSize = 16f
                }
            }
        }
    }
}

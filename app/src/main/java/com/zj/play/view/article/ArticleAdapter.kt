package com.zj.play.view.article

import android.content.Context
import android.text.Html
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import com.zj.core.Play
import com.zj.core.util.setSafeListener
import com.zj.play.R
import com.zj.play.model.Article
import com.zj.play.network.CollectRepository
import com.zj.play.view.account.LoginActivity

class ArticleAdapter(context: Context, layoutId: Int, articleList: ArrayList<Article>) :
    CommonAdapter<Article>(context, layoutId, articleList) {

    override fun convert(holder: ViewHolder, t: Article, position: Int) {
        val articleLlItem = holder.getView<RelativeLayout>(R.id.articleLlItem)
        val articleIvImg = holder.getView<ImageView>(R.id.articleIvImg)
        val articleTvAuthor = holder.getView<TextView>(R.id.articleTvAuthor)
        val articleTvNew = holder.getView<TextView>(R.id.articleTvNew)
        val articleTvTop = holder.getView<TextView>(R.id.articleTvTop)
        val articleTvTime = holder.getView<TextView>(R.id.articleTvTime)
        val articleTvTitle = holder.getView<TextView>(R.id.articleTvTitle)
        val articleTvChapterName = holder.getView<TextView>(R.id.articleTvChapterName)
        val articleTvCollect = holder.getView<ImageView>(R.id.articleIvCollect)
        articleTvTitle.text = Html.fromHtml(t.title)
        articleTvChapterName.text = t.superChapterName
        articleTvAuthor.text = if (TextUtils.isEmpty(t.author)) t.shareUser else t.author
        articleTvTime.text = t.niceShareDate
        if (!TextUtils.isEmpty(t.envelopePic)) {
            articleIvImg.visibility = View.VISIBLE
            Glide.with(mContext).load(t.envelopePic).into(articleIvImg)
        }
        articleTvTop.visibility = if (t.type > 0) View.VISIBLE else View.GONE
        articleTvNew.visibility = if (t.fresh) View.VISIBLE else View.GONE
        if (t.collect) {
            articleTvCollect.setImageResource(R.drawable.ic_favorite_black_24dp)
        } else {
            articleTvCollect.setImageResource(R.drawable.ic_favorite_border_black_24dp)
        }
        articleTvCollect.setSafeListener {
            if (Play.isLogin()) {
                if (!t.collect) {
                    articleTvCollect.setImageResource(R.drawable.ic_favorite_black_24dp)
                } else {
                    articleTvCollect.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                }
                setCollect(t.id, !t.collect)
            } else {
                LoginActivity.actionStart(mContext)
            }
        }
        articleLlItem.setOnClickListener {
            ArticleActivity.actionStart(mContext, t.title, t.link)
        }
    }

    private fun setCollect(id: Int, collect: Boolean) {
        if (collect) {
            CollectRepository.toCollect(id)
        } else {
            CollectRepository.cancelCollect(id)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}

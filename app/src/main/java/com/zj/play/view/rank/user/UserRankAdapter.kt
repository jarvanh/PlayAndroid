package com.zj.play.view.rank.user

import android.content.Context
import android.widget.TextView
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import com.zj.play.R
import com.zj.play.model.Ranks
import kotlin.collections.ArrayList

class UserRankAdapter(context: Context, layoutId: Int, rankList: ArrayList<Ranks>) :
    CommonAdapter<Ranks>(context, layoutId, rankList) {
    override fun convert(holder: ViewHolder, t: Ranks, position: Int) {
        val rankAdTvUsername = holder.getView<TextView>(R.id.rankAdTvUsername)
        val rankAdTvRank = holder.getView<TextView>(R.id.rankAdTvRank)
        val rankAdTvCoinCount = holder.getView<TextView>(R.id.rankAdTvCoinCount)
        val rankAdTvLevel = holder.getView<TextView>(R.id.rankAdTvLevel)
        rankAdTvUsername.text = t.userName
        rankAdTvRank.text = "增减原因：${t.reason}"
        rankAdTvCoinCount.text = "积分${t.coinCount}"
        rankAdTvLevel.text = "${t.desc}"
    }

}

package com.ankkumar.hellogold.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ankkumar.hellogold.R
import com.ankkumar.hellogold.app.GoldApp
import com.ankkumar.hellogold.model.response.Data


class MainSpotPriceAdapter : RecyclerView.Adapter<MainSpotPriceAdapter.MainSpotPriceViewHolder>() {


    lateinit var goldDataList: MutableList<Data>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainSpotPriceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_spot_price, parent, false)
        return MainSpotPriceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return goldDataList.size
    }

    override fun onBindViewHolder(holder: MainSpotPriceViewHolder, position: Int) {
        holder.bindData(goldDataList[position])
    }


    class MainSpotPriceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var buyText: TextView
        var sellText: TextView
        var spotText: TextView
        var timeStampTxt: TextView
        var ctx: Context

        init {
            buyText = itemView.findViewById(R.id.gold_buy_txt)
            sellText = itemView.findViewById(R.id.gold_sell_txt)
            spotText = itemView.findViewById(R.id.gold_spot_price_txt)
            timeStampTxt = itemView.findViewById(R.id.timestamp_txt)

            ctx = GoldApp.app!!.applicationContext
        }

        fun bindData(data: Data) {
            buyText.text = String.format(ctx.getString(R.string.format_buy), data.buy)
            sellText.text = String.format(ctx.getString(R.string.format_sell), data.sell)
            spotText.text = String.format(ctx.getString(R.string.format_spot_price), data.spotPrice)
            timeStampTxt.text = data.timestamp
        }
    }

}
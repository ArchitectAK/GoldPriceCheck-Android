package com.ankkumar.hellogold.ui

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.ankkumar.hellogold.remote.RetrofitInitializer
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import com.ankkumar.hellogold.app.GoldApp
import com.ankkumar.hellogold.db.AppDatabase
import com.ankkumar.hellogold.model.response.Data
import com.ankkumar.hellogold.model.response.SpotPrice
import com.ankkumar.hellogold.util.PreferenceUtil


class MainViewModel {
    var toolbarEmail = ObservableField<String>()
    var isRefreshing = ObservableBoolean()
    var isSwipeColorSet = ObservableBoolean(true)
    var isRecyclerViewCustom = ObservableBoolean(true)

    var adapter: MainSpotPriceAdapter = MainSpotPriceAdapter()

    var onRefreshListener: SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener { onFetchData() }


    // handling fab click event
    fun onClickFetchData(view: View) {
        onStartFetchData()
        RetrofitInitializer.getinstance().createSpotPrice(object : RetrofitInitializer.OnCallback<SpotPrice> {
            override fun onReturn(body: SpotPrice) {
                onSuccessFetchData(body)
            }

            override fun onNeedCheck(body: SpotPrice) {
                onNeedCheckData()
            }

            override fun onFailed() {
                onErrorData()
            }
        })
    }

    init {
        val dataList = AppDatabase.getAppDatabase(GoldApp.app!!.applicationContext).dataDao().all
        adapter.goldDataList = dataList as MutableList<Data>
        adapter.notifyDataSetChanged()
        toolbarEmail.set(PreferenceUtil.instance.getString(PreferenceUtil.EMAIL_ADDRESS, ""))
        onFetchData()
    }

    private fun onFetchData() {
        onStartFetchData()
        RetrofitInitializer.getinstance().createSpotPrice(object : RetrofitInitializer.OnCallback<SpotPrice> {
            override fun onReturn(body: SpotPrice) {
                onSuccessFetchData(body)
            }

            override fun onNeedCheck(body: SpotPrice) {
                onNeedCheckData()
            }

            override fun onFailed() {
                onErrorData()
            }
        })
    }

    private fun onStartFetchData() {
        isRefreshing.set(true)
    }

    private fun onSuccessFetchData(body: SpotPrice) {
        isRefreshing.set(false)
        body.data?.let { AppDatabase.getAppDatabase(GoldApp.app!!.applicationContext).dataDao().insertEachData(it) }
        body.data?.let { adapter.goldDataList.add(0, it) }
        adapter.notifyDataSetChanged()
    }

    private fun onNeedCheckData() {
        isRefreshing.set(false)
    }

    private fun onErrorData() {
        isRefreshing.set(false)
    }
}
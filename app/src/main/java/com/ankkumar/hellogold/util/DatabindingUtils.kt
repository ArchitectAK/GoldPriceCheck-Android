package com.ankkumar.hellogold.util


import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.ankkumar.hellogold.ui.MainSpotPriceAdapter


object DatabindingUtils {

    @BindingAdapter("colorScheme")
    fun setColorSchemes(layout: SwipeRefreshLayout, isSet: Boolean) {
        if (isSet) {
            layout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
            )
        }
    }

    @BindingAdapter("initLayout")
    fun setInitLayout(recyclerView: RecyclerView, isCustom: Boolean) {
        if (isCustom) {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        }
    }

    @BindingAdapter("adapter")
    fun setAdapter(recyclerView: RecyclerView, adapter: MainSpotPriceAdapter) {
        recyclerView.adapter = adapter
    }

    @BindingAdapter("emailAddress")
    fun setEmailAddress(toolbar: Toolbar, email: String) {
        toolbar.title = email
    }
}

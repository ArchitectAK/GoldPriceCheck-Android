package com.ankkumar.hellogold.util


import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankkumar.hellogold.ui.MainSpotPriceAdapter


object DatabindingUtils {

    @BindingAdapter("colorScheme")
    fun setColorSchemes(
        layout: androidx.swiperefreshlayout.widget.SwipeRefreshLayout,
        isSet: Boolean
    ) {
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
    fun setInitLayout(recyclerView: androidx.recyclerview.widget.RecyclerView, isCustom: Boolean) {
        if (isCustom) {
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context)
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    @BindingAdapter("adapter")
    fun setAdapter(
        recyclerView: androidx.recyclerview.widget.RecyclerView,
        adapter: MainSpotPriceAdapter
    ) {
        recyclerView.adapter = adapter
    }

    @BindingAdapter("emailAddress")
    fun setEmailAddress(toolbar: Toolbar, email: String) {
        toolbar.title = email
    }
}

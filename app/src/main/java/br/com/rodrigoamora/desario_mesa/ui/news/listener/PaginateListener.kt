package br.com.rodrigoamora.desario_mesa.ui.news.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginateListener(linearLayoutManager: LinearLayoutManager?) : RecyclerView.OnScrollListener() {

    var current_page : Int = 1
    var loading = true
    var previousTotal : Int = 0
    var visibleThreshold : Int = 5

    var firstVisibleItem : Int? = null
    var visibleItemCount : Int? = null
    var totalItemCount : Int? = null

    var mLinearLayoutManager: LinearLayoutManager = linearLayoutManager!!

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.getChildCount()
        totalItemCount = mLinearLayoutManager.getItemCount()
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount!! > previousTotal) {
                loading = false
                previousTotal = totalItemCount!!
            }
        }

        if (!loading && (totalItemCount!! - visibleItemCount!!) <= (firstVisibleItem!! + visibleThreshold)) {
            current_page++
            onLoadMore(current_page)
            loading = true
        }
    }

    abstract fun onLoadMore(current_page: Int)

}
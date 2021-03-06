package br.com.rodrigoamora.desario_mesa.ui.news

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rodrigoamora.desario_mesa.R
import br.com.rodrigoamora.desario_mesa.dao.SettingsDao
import br.com.rodrigoamora.desario_mesa.model.News
import br.com.rodrigoamora.desario_mesa.ui.news.adapter.NewsAdapter
import br.com.rodrigoamora.desario_mesa.ui.news.details.DetailsNewsFragment
import br.com.rodrigoamora.desario_mesa.ui.news.listener.OnItemListClickListener
import br.com.rodrigoamora.desario_mesa.ui.news.presenter.HighlightsPresenter
import br.com.rodrigoamora.desario_mesa.ui.news.presenter.NewsPresenter
import br.com.rodrigoamora.desario_mesa.util.FragmentUtil
import kotlinx.android.synthetic.main.fragment_news.*
import java.util.Timer
import java.util.TimerTask


class ListNewsFragment : Fragment(), NewsContract.View {

    lateinit var highlightsPresenter: HighlightsPresenter
    lateinit var newsPresenter : NewsPresenter
    lateinit var recyclerViewHighlights : RecyclerView
    lateinit var recyclerViewNews : RecyclerView

    lateinit var timer: Timer
    lateinit var timerTask: TimerTask
    lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureRecyclerViewOfHighlights()
        configureRecyclerViewOfNews()
        instantiatePresenter()
        initializeTimerTask()
    }

    override fun onStop() {
        timer.cancel()
        super.onStop()
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(activity!!.baseContext, message, Toast.LENGTH_LONG).show()
    }

    override fun searchHighlights() {
        highlightsPresenter.searchHighlights()
    }

    override fun searchNews() {
        newsPresenter.searchNews()
    }

    override fun populateRecyclerViewOfHighlights(newsList: List<News>?) {
        val adapter = NewsAdapter(activity!!.applicationContext, newsList)
        recyclerViewHighlights.adapter = adapter

        adapter.itemClickListener(object : OnItemListClickListener<News> {
            override fun onItemClick(news: News) {
                goToDetailsNewsFragment(news)
            }
        })
    }

    override fun populateRecyclerViewOfNews(newsList: List<News>?) {
        val adapter = NewsAdapter(activity!!.applicationContext, newsList)
        recyclerViewNews.adapter = adapter

        adapter.itemClickListener(object : OnItemListClickListener<News> {
            override fun onItemClick(news: News) {
                goToDetailsNewsFragment(news)
            }
        })
    }

    private fun configureRecyclerViewOfHighlights() {
        val linearLayout = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        val dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL)
        recyclerViewHighlights = list_highlights
        recyclerViewHighlights.addItemDecoration(dividerItemDecoration)
        recyclerViewHighlights.setHasFixedSize(true)
        recyclerViewHighlights.setItemAnimator(DefaultItemAnimator())
        recyclerViewHighlights.setLayoutManager(linearLayout)
        recyclerViewHighlights.setNestedScrollingEnabled(false)
    }

    private fun configureRecyclerViewOfNews() {
        val linearLayout = LinearLayoutManager(activity)
        val dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerViewNews = list_news
        recyclerViewNews.addItemDecoration(dividerItemDecoration)
        recyclerViewNews.setHasFixedSize(true)
        recyclerViewNews.setItemAnimator(DefaultItemAnimator())
        recyclerViewNews.setLayoutManager(linearLayout)
        recyclerViewNews.setNestedScrollingEnabled(false)
    }

    private fun instantiatePresenter() {
        highlightsPresenter =
            HighlightsPresenter(activity!!.baseContext)
        highlightsPresenter.view = this

        newsPresenter =
            NewsPresenter(activity!!.baseContext)
        newsPresenter.view = this
    }

    private fun goToDetailsNewsFragment(news: News) {
        val bundle = Bundle()
        bundle.putSerializable("news", news)

        FragmentUtil.changeFragment(
            R.id.container,
            DetailsNewsFragment(),
            activity?.supportFragmentManager!!,
            true,
            bundle
        )
    }

    fun initializeTimerTask() {
        handler = Handler()
        timer = Timer()

        timerTask = object : TimerTask(){
            override fun run() {
                handler.post {
                    searchHighlights()
                    searchNews()
                }
            }
        }

        val delay: Long = 1000
        val timeToRefresh = SettingsDao.getTimeToRefreshNews(activity?.baseContext!!)
        timer.schedule(timerTask,
                        delay,
                        timeToRefresh)
    }

}
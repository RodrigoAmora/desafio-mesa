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
import br.com.rodrigoamora.desario_mesa.model.News
import br.com.rodrigoamora.desario_mesa.ui.news.adapter.NewsAdapter
import br.com.rodrigoamora.desario_mesa.ui.news.details.DetailsNewsFragment
import br.com.rodrigoamora.desario_mesa.ui.news.listener.OnItemListClickListener
import br.com.rodrigoamora.desario_mesa.util.FragmentUtil
import kotlinx.android.synthetic.main.fragment_news.*
import java.util.Timer
import java.util.TimerTask


class NewsFragment : Fragment(), NewsContract.View {

    lateinit var presenter : NewsPresenter
    lateinit var recyclerView : RecyclerView

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

        configureRecyclerView()
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

    override fun searchNews() {
        presenter.searchNews()
    }

    override fun populateRecyclerView(newsList: List<News>?) {
        val adapter = NewsAdapter(activity!!.applicationContext, newsList)
        recyclerView.adapter = adapter

        adapter.itemClickListener(object : OnItemListClickListener<News> {
            override fun onItemClick(news: News) {
                goToDetailsNewsFragment(news)
            }
        })
    }

    private fun configureRecyclerView() {
        val linearLayout = LinearLayoutManager(activity)
        val dividerItemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView = list_news
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemAnimator(DefaultItemAnimator())
        recyclerView.setLayoutManager(linearLayout)
        recyclerView.setNestedScrollingEnabled(false)
    }

    private fun instantiatePresenter() {
        presenter = NewsPresenter(activity!!.baseContext)
        presenter.view = this
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
                //use a handler to run a toast that shows the current timestamp
                handler.post(Runnable {
                    searchNews()
                })
            }
        }

        timer.schedule(timerTask, 1000, 3000)
    }

}
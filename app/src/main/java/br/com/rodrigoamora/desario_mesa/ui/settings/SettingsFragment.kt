package br.com.rodrigoamora.desario_mesa.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.rodrigoamora.desario_mesa.R
import br.com.rodrigoamora.desario_mesa.dao.SettingsDao
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        intiViews()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val time: Long = if (position == 0) {
            30*1000L
        } else if (position == 1) {
            45*1000L
        } else if (position == 2) {
            60*1000L
        } else if (position == 3) {
            180*1000L
        } else {
            300*1000L
        }

        SettingsDao.saveTimeToRefreshNews(activity?.baseContext!!, time)
    }

    private fun intiViews() {
        val listTime = resources.getStringArray(R.array.list_time)
        val arrayAdapter = context?.let { ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, listTime) }
        val spTime = sp_time_to_refresh_news
        if (arrayAdapter != null) {
            spTime?.adapter = arrayAdapter
        }
        spTime.setOnItemSelectedListener(this)
    }

}
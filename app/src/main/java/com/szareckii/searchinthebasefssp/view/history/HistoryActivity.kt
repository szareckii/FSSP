package com.szareckii.searchinthebasefssp.view.history

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.szareckii.searchinthebasefssp.R
import com.szareckii.searchinthebasefssp.room.HistoryEntity
import com.szareckii.searchinthebasefssp.room.StateHistoryEntity
import com.szareckii.searchinthebasefssp.utils.regionMapNumber
import com.szareckii.searchinthebasefssp.view.base.BaseActivity
import com.szareckii.searchinthebasefssp.view.main.MainActivity
import com.szareckii.searchinthebasefssp.view.main.SearchDialogFragment
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryActivity : BaseActivity<StateHistoryEntity>() {

    override val model: HistoryViewModel by viewModel()

    private val adapter: HistoryAdapter by lazy { HistoryAdapter(onListItemClickListener) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setActionbarHomeButtonAsUp()
        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData()
    }

    // Слушатель получает от адаптера необходимые данные и запускает новый экран
    private val onListItemClickListener: HistoryAdapter.OnListItemClickListener =
        object : HistoryAdapter.OnListItemClickListener {
            override fun onItemClick(data: HistoryEntity) {
                Log.e("11111111111111111", "startActivity1")
                startActivity(
                    regionMapNumber[data.region]?.let {
                        MainActivity.getIntent(
                            this@HistoryActivity,
                            it,
                            data.lastname,
                            data.firstname,
                            data.secondname!!,
                            data.birthdate!!
                        )
                    }
                )
            }
        }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun iniViewModel() {
        if (history_activity_recyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        model.subscribe().observe(this@HistoryActivity, { renderData(it) })
    }

    private fun initViews() {
        history_activity_recyclerview.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun renderData(stateHistoryEntity: StateHistoryEntity) {
        when (stateHistoryEntity) {
            is StateHistoryEntity.Success -> {
                showViewWorking()
                stateHistoryEntity.data?.let {
                    val dataModel = stateHistoryEntity.data
                    if (it.isEmpty()) {
                        showAlertDialog(
                            getString(R.string.dialog_tittle_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        adapter.setData(dataModel)
                    }
                }
            }
            is StateHistoryEntity.Loading -> {
                showViewLoading()
                if (stateHistoryEntity.progress != null) {
                    progress_bar_horizontal.visibility = View.VISIBLE
                    progress_bar_round.visibility = View.GONE
                    progress_bar_horizontal.progress = stateHistoryEntity.progress
                } else {
                    progress_bar_horizontal.visibility = View.GONE
                    progress_bar_round.visibility = View.VISIBLE
                }
            }
            is StateHistoryEntity.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_textview_stub), stateHistoryEntity.error.message)
            }
        }
    }

    private fun showViewWorking() {
        loading_frame_layout.visibility = View.GONE
    }

    private fun showViewLoading() {
        loading_frame_layout.visibility = View.VISIBLE
    }
}

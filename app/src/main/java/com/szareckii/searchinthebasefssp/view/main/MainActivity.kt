package com.szareckii.searchinthebasefssp.view.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.szareckii.searchinthebasefssp.R
import com.szareckii.searchinthebasefssp.model.data.result.AppState
import com.szareckii.searchinthebasefssp.utils.network.isOnline
import com.szareckii.searchinthebasefssp.utils.regionMapNumber
import com.szareckii.searchinthebasefssp.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<AppState>() {

    private val adapter: MainAdapter by lazy { MainAdapter() }

    lateinit var lastName : String
    lateinit var firstName : String
    lateinit var secondName : String
    lateinit var birth : String
    lateinit var reg : String

    override val model: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initViews()
        search_fab.setOnClickListener(fabClickListener)
    }

    private fun initViewModel() {
        if (main_activity_recyclerview.adapter != null) {
            throw IllegalStateException(getString(R.string.viewmodel_is_null))
        }
        model.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    private fun initViews() {
        search_fab.setOnClickListener(fabClickListener)
        main_activity_recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        main_activity_recyclerview.adapter = adapter
    }

    private val fabClickListener: android.view.View.OnClickListener =
            android.view.View.OnClickListener {
                val searchDialogFragment = SearchDialogFragment.newInstance()
                searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
                searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
            }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
            object : SearchDialogFragment.OnSearchClickListener {
                override fun onClick(
                    region: String,
                    lastname: String,
                    firstname: String,
                    secondname: String,
                    birthdate: String
                ) {
                    isNetworkAvailable = isOnline(applicationContext)

                    if (isNetworkAvailable) {
                        reg = region
                        lastName = lastname
                        firstName = firstname
                        secondName = secondname
                        birth = birthdate

                        model.getData(region,
                                lastname,
                                firstname,
                                secondname,
                                birthdate,
                                isNetworkAvailable
                        )
                    } else {
                        showNoInternetConnectionDialog()
                    }
                }
            }


    override fun renderData(appState: AppState) {
        renderPhysical()
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                val dataModel = appState.data
                if (dataModel?.responseResult?.resultList?.get(0)?.resultDetailList?.size == 0
                        || dataModel == null) {

                    showAlertDialog(
                        getString(R.string.dialog_tittle_sorry),
                        getString(R.string.empty_server_response_on_success)
                    )

                } else {
                    adapter.setData(dataModel)
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    progress_bar_horizontal.visibility = VISIBLE
                    progress_bar_round.visibility = GONE
                    progress_bar_horizontal.progress = appState.progress
                } else {
                    progress_bar_horizontal.visibility = GONE
                    progress_bar_round.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_textview_stub), appState.error.message)
            }
        }
    }

    fun renderPhysical() {
        lastnameTextView.text = lastName
        firstnameTextView.text = firstName
        secondnameTextView.text = secondName
        birthdateTextView.text = birth
        regionTextView.text = regionMapNumber[reg]
    }

    private fun showViewWorking() {
        loading_frame_layout.visibility = GONE
    }

    private fun showViewLoading() {
        loading_frame_layout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "com.szareckii.searchinthebasefssp.searchdialogfragment"
    }
}


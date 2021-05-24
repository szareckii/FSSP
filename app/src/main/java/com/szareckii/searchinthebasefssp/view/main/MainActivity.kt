package com.szareckii.searchinthebasefssp.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.szareckii.searchinthebasefssp.R
import com.szareckii.searchinthebasefssp.model.data.result.AppStateResult
import com.szareckii.searchinthebasefssp.utils.network.isOnline
import com.szareckii.searchinthebasefssp.utils.regionMap
import com.szareckii.searchinthebasefssp.utils.regionMapNumber
import com.szareckii.searchinthebasefssp.view.base.BaseActivity
import com.szareckii.searchinthebasefssp.view.history.HistoryActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.loading_frame_layout
import kotlinx.android.synthetic.main.activity_main.progress_bar_horizontal
import kotlinx.android.synthetic.main.activity_main.progress_bar_round
import kotlinx.android.synthetic.main.loading_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<AppStateResult>() {

    override val model: MainViewModel by viewModel()

    private val adapter: MainAdapter by lazy { MainAdapter() }

    lateinit var lastName : String
    lateinit var firstName : String
    lateinit var secondName : String
    lateinit var birth : String
    lateinit var reg : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initViews()
        search_fab.setOnClickListener(fabClickListener)
        setData()
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

    private val fabClickListener: View.OnClickListener =
            View.OnClickListener {
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

                    regionMap[reg]?.let {
                        model.getData(
                            it,
                            lastname,
                            firstname,
                            secondname,
                            birthdate,
                            isNetworkAvailable
                        )
                    }
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun renderPhysical() {
        lastnameTextView.text = lastName
        firstnameTextView.text = firstName
        secondnameTextView.text = secondName
        birthdateTextView.text = birth
//        regionTextView.text = regionMapNumber[reg]
        regionTextView.text = reg
    }

    fun renderData(appStateResult: AppStateResult) {
        renderPhysical()
        when (appStateResult) {
            is AppStateResult.Success -> {
                showViewWorking()
                appStateResult.data?.let {
                    val dataModel = appStateResult.data
                    if (dataModel.responseResult?.resultList?.get(0)?.resultDetailList?.size == 0) {
                        showAlertDialog(
                            getString(R.string.dialog_tittle_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        adapter.setData(dataModel)
                    }
                }
            }
            is AppStateResult.Loading -> {
                showViewLoading()
                if (appStateResult.progress != null) {
                    progress_bar_horizontal.visibility = View.VISIBLE
                    progress_bar_round.visibility = View.GONE
                    progress_bar_horizontal.progress = appStateResult.progress
                } else {
                    progress_bar_horizontal.visibility = View.GONE
                    progress_bar_round.visibility = View.VISIBLE
                }
            }
            is AppStateResult.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_textview_stub), appStateResult.error.message)
            }
        }
    }

    private fun showViewWorking() {
        loading_frame_layout.visibility = GONE
    }

    private fun showViewLoading() {
        loading_frame_layout.visibility = VISIBLE
    }

    // Достаём данные из бандла
    private fun setData() {
        val bundle = intent.extras
        if (bundle != null) {
            reg = bundle.getString(REGION_NAME).toString()
            lastName = bundle.getString(LASTNAME).toString()
            firstName = bundle.getString(FIRSTNAME).toString()
            secondName = bundle.getString(SECONDNAME).toString()
            birth = bundle.getString(BIRTHDATE).toString()

            regionTextView.text = reg
            lastnameTextView.text = lastName
            firstnameTextView.text = firstName
            secondnameTextView.text = secondName
            birthdateTextView.text = birth

            regionMap[reg]?.let {
                model.getData(
                    it,
                    lastName,
                    firstName,
                    secondName,
                    birth,
                    isNetworkAvailable
                )
            }
        }
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "com.szareckii.searchinthebasefssp.searchdialogfragment"
        private const val REGION_NAME = "f76a288a-5dcc-43f1-ba89-7fe1d53f63b0"
        private const val LASTNAME = "0eeb92aa-520b-4fd1-bb4b-027fbf963d9a"
        private const val FIRSTNAME = "6e4b154d-e01f-4953-a404-639fb3bf7281"
        private const val SECONDNAME = "40d7382e-7717-4f33-a45d-1ec1dd4b6020"
        private const val BIRTHDATE = "55c981b3-0536-4d28-a832-0dda021adcda"

        fun getIntent(
            context: Context,
            region: String,
            lastname: String,
            firstname: String,
            secondname: String?,
            birthdate: String?
        ): Intent = Intent(context, MainActivity::class.java).apply {
            putExtra(REGION_NAME, region)
            putExtra(LASTNAME, lastname)
            putExtra(FIRSTNAME, firstname)
            putExtra(SECONDNAME, secondname)
            putExtra(BIRTHDATE, birthdate)
        }
    }
}


package com.szareckii.searchinthebasefssp.view.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.szareckii.searchinthebasefssp.R
import com.szareckii.searchinthebasefssp.model.data.physical.AppStatePhysical
import com.szareckii.searchinthebasefssp.presenter.Presenter
import com.szareckii.searchinthebasefssp.utils.regionMapNumber
import com.szareckii.searchinthebasefssp.view.base.BaseActivity
import com.szareckii.searchinthebasefssp.view.base.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<AppStatePhysical>() {

    lateinit var lastName : String
    lateinit var firstName : String
    lateinit var secondName : String
    lateinit var birth : String
    lateinit var reg : String

    override fun createPresenter(): Presenter<AppStatePhysical, View> {
        return MainPresenterImpl()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabListener()
    }

    private fun fabListener() {
        search_fab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object : SearchDialogFragment.OnSearchClickListener {
                override fun onClick(
                        region: String,
                        lastname: String,
                        firstname: String,
                        secondname: String,
                        birthdate: String

                ) {
                    reg = region
                    lastName = lastname
                    firstName = firstname
                    secondName = secondname
                    birth = birthdate

                    presenter.getDataPhysical(
                        region,
                        lastname,
                        firstname,
                        secondname,
                        birthdate,
            true
                    )
                }
            })
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    override fun renderData(appStatePhysical: AppStatePhysical) {
        renderPhysical()
        when (appStatePhysical) {
            is AppStatePhysical.Success -> {
                val dataModel = appStatePhysical.data
                if (dataModel == null) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    taskTextView.text = dataModel.responsePhysical?.task ?: "Пусто"
                }
            }
            is AppStatePhysical.Loading -> {
                showViewLoading()
                if (appStatePhysical.progress != null) {
                    progress_bar_horizontal.visibility = VISIBLE
                    progress_bar_round.visibility = GONE
                    progress_bar_horizontal.progress = appStatePhysical.progress
                } else {
                    progress_bar_horizontal.visibility = GONE
                    progress_bar_round.visibility = VISIBLE
                }
            }
            is AppStatePhysical.Error -> {
                showErrorScreen(appStatePhysical.error.message)
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

    private fun showErrorScreen(error: String?) {
        showViewError()
        error_textview.text = error ?: getString(R.string.undefined_error)
        reload_button.setOnClickListener {
            presenter.getDataPhysical("", "", "",null, "", true)
        }
    }

    private fun showViewSuccess() {
        success_linear_layout.visibility = VISIBLE
        loading_frame_layout.visibility = GONE
        error_linear_layout.visibility = GONE
    }

    private fun showViewLoading() {
        success_linear_layout.visibility = GONE
        loading_frame_layout.visibility = VISIBLE
        error_linear_layout.visibility = GONE
    }

    private fun showViewError() {
        success_linear_layout.visibility = GONE
        loading_frame_layout.visibility = GONE
        error_linear_layout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "com.szareckii.searchinthebasefssp.searchdialogfragment"
    }
}


package com.szareckii.searchinthebasefssp.view.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.szareckii.searchinthebasefssp.R
import com.szareckii.searchinthebasefssp.model.data.result.AppState
import com.szareckii.searchinthebasefssp.presenter.Presenter
import com.szareckii.searchinthebasefssp.utils.network.isOnline
import com.szareckii.searchinthebasefssp.utils.ui.AlertDialogFragment
import com.szareckii.searchinthebasefssp.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: AppState)

    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message).show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "com.szareckii.searchinthebasefssp.baseactivity"
    }
}

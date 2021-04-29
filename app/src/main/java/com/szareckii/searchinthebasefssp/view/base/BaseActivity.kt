package com.szareckii.searchinthebasefssp.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.szareckii.searchinthebasefssp.model.data.physical.AppStatePhysical
import com.szareckii.searchinthebasefssp.presenter.Presenter

abstract class BaseActivity<T : AppStatePhysical> : AppCompatActivity(), View  {

    protected lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenter(): Presenter<T, View>

    abstract override fun renderData(appStatePhysical: AppStatePhysical)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}

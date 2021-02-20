package ru.gressor.skyengdictionary.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.entities.DictData

abstract class BaseView: Fragment(), MainContract.View {
    protected lateinit var presenter: MainContract.Presenter

    protected abstract fun createPresenter(): MainContract.Presenter

    abstract override fun renderData(data: DictData)

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
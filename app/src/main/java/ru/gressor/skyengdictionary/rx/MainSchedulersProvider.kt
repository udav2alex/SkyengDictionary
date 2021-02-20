package ru.gressor.skyengdictionary.rx

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class MainSchedulersProvider: SchedulersProvider {
    override fun io(): Scheduler = Schedulers.io()
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}
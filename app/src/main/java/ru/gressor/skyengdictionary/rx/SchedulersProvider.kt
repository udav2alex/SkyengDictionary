package ru.gressor.skyengdictionary.rx

import io.reactivex.rxjava3.core.Scheduler

interface SchedulersProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}
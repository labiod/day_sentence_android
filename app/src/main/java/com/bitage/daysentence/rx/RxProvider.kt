package com.bitage.daysentence.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxProvider {
    fun getIoScheduler(): Scheduler {
        return Schedulers.io()
    }

    fun getMainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    fun getComputationScheduler(): Scheduler {
        return Schedulers.computation()
    }

    fun getNewThreadScheduler(): Scheduler {
        return Schedulers.newThread()
    }
}
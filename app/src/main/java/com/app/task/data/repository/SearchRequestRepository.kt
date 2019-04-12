package com.app.task.data.repositorygetAll

import com.app.task.data.dao.SearchRequestDao
import com.app.task.data.model.SearchRequest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchRequestRepository(private val dao: SearchRequestDao){

    fun getAll() = Observable.fromCallable { dao.getAll() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getLastTenQueries() = Observable.fromCallable { dao.getLastTenQueries() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun insert(searchRequest: SearchRequest) = Observable.fromCallable { dao.insert(searchRequest) }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}
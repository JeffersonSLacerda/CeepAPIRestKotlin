package com.rav.test.ceep.ui.base

import android.annotation.SuppressLint
import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    @SuppressLint("StaticFieldLeak")
    private var context: Context? = null

    private var mNavigator: BaseNavigator? = null

    private var mViewDataBinding: ViewDataBinding? = null

    fun getNavigator(): BaseNavigator{
        return mNavigator!!
    }

    infix fun setNavigator(navigator: BaseNavigator) {
        this.mNavigator = navigator
    }

    fun getContext() : Context {
        return context!!
    }

    fun setContext( context: Context){
        this.context = context
    }

}
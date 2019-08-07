package com.rav.test.ceep.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity <out T: ViewDataBinding, out V: BaseViewModel>: AppCompatActivity(), BaseFragment.CallBack {

    private lateinit var mViewDataBinding: T

    private var mViewModel: V? = null

    abstract fun getBindVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding(){
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = mViewModel ?: getViewModel()
        this.mViewModel!!.setContext(this)
        mViewDataBinding.setVariable(getBindVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    fun binding(): T{
        return mViewDataBinding
    }

    override fun onFragementAttached() { }

    override fun onFragmentDetached(tag: String) { }
}
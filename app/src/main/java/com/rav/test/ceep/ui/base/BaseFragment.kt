package com.rav.test.ceep.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<out T: ViewDataBinding, out V: BaseViewModel> : Fragment(){

    private var mActivity: BaseActivity<*, *>? = null
    private lateinit var mViewDataBinding: T

    private var mViewModel: V? = null

    abstract fun getBindVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>){
            this.mActivity = context
            context.onFragemnteAttached()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mViewDataBinding.root
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        mViewModel!!.setContext(this.context!!)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(getBindVariable(),mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    fun getBaseActivity(): BaseActivity<*, *>{
        return mActivity!!
    }

    fun binding(): T{
        return mViewDataBinding
    }

    interface CallBack{

        fun onFragemnteAttached()

        fun onFragmentDetached(tag: String)
    }

}
package other.mvvm.page_fragment

fun pageFragmentKt(
        basePackageName: String?,
        fragmentClass: String,
        layoutName: String,
        packageName: String,
        beanName: String,
        viewModelEnable: Boolean,
        needPaging3Enable: Boolean,
        adapterName: String,
) =
        if (needPaging3Enable) {


            """
package ${packageName}


import ${packageName}.databinding.Fragment${beanName}Binding
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import ${packageName}.${beanName}Bean
import com.afanticar.common.bean.PageInfo
import com.afanticar.common.fragment.CommonPageViewFragment
import com.afanticar.common.net.NetCodeFlowCollector
import com.afanticar.common.utils.ToastUtils
import com.afanticar.network.bean.*
import kotlinx.coroutines.flow.FlowCollector
import ${packageName}.R

@AndroidEntryPoint
class ${fragmentClass}Fragment : CommonPageViewFragment
<Fragment${beanName}Binding,${fragmentClass}FragmentViewModel,${beanName}Bean>(R.layout.${layoutName}) {

   private val m${adapterName}Adapter by lazy { ${adapterName}Adapter() }


    @InternalCoroutinesApi
    override fun initView() {
        setRefreshViewAndRecyclerView(
            mBinding?.refreshLayout,
            mBinding.recyclerview,
            m${adapterName}Adapter,
            mBinding.loading
        )
    }

   @InternalCoroutinesApi
    override fun loadData() {
    lifecycleScope.launch {
            mViewModel.网络请求(current, size)
                .collect(object : FlowCollector<AftResult<CommonPageBean<${beanName}Bean>>> {
                    override suspend fun emit(value: AftResult<CommonPageBean<${beanName}Bean>>) {
                        value.onSuccess {
                            it?.let {
                                 doWithListData(it.list?.toMutableList(), it.total, it.page_num)
                            }
                        }
                        value.onFailure {
                            loadFailed()
                            ToastUtils.showShort(it?.localizedMessage)
                        }
                    }
                })
    }
    }

//    override fun initOtherViewModel() {
//    }


}
"""

        } else {
            if (viewModelEnable) {


                """
package ${packageName}

import android.view.View
import ${packageName}.R
import ${packageName}.${beanName}Adapter
import c${packageName}. ${beanName}Bean
import ${packageName}.databinding.Fragment${fragmentClass}Binding
import com.afanticar.common.common_page_new_utils.PageNewFragment
import com.afanticar.common.common_page_new_utils.bean.LoadStatusBean
import com.afanticar.common.widget.LinearSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi


@AndroidEntryPoint
class ${fragmentClass}Fragment : PageNewFragment<Fragment${fragmentClass}Binding,${fragmentClass}ViewModel, ${beanName}Bean>(R.layout.${layoutName}) {

 private val m${beanName}Adapter by lazy { ${beanName}Adapter() }

    val mLinearSpacingItemDecoration by lazy {
        LinearSpacingItemDecoration(
            14,
            false,
            false,
            false,
            true,
        )
    }

    override fun initData() {
    }
    
    override fun initDataCallback() {
        mViewModel.xData.observe(this, {
            setPageAdapterDataAndResetViewStatus(it)
        })

    }
    
     @InternalCoroutinesApi
    override fun loadData(loadStatus: LoadStatusBean) {
        mViewModel.getXData()
    }
    
    @InternalCoroutinesApi
    override fun initView() {
        setRefreshViewAndRecyclerView(
            mBinding?.refreshLayout,
            mBinding.recyclerview,
            m${beanName}Adapter,
            mBinding.loading,
            mBinding.scrollView
        )

        mBinding?.recyclerview.removeItemDecoration(mLinearSpacingItemDecoration)
        mBinding?.recyclerview.addItemDecoration(
            mLinearSpacingItemDecoration
        )

    }
    
    
       companion object {
        fun newInstance() =
            ${fragmentClass}Fragment().apply {
                this.arguments = Bundle().apply {

                   // putString("medalId", medalId)
           
                }
            }
    }
    

}
"""


            } else {
                """
package ${packageName}
import com.afanticar.base.ui.BaseFragment

class ${fragmentClass}Fragment : BaseFragment(R.layout.${layoutName}) {

    override fun initView() {
    }

    override fun initData() {
    }

    override fun onEvent() {
    }
}
    """
            }
        }
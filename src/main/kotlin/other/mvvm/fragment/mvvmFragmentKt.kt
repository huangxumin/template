package other.mvvm.fragment

fun mvvmFragmentKt(
        basePackageName: String?,
        fragmentClass: String,
        layoutName: String,
        packageName: String,
        beanName: String,
        viewModelEnable: Boolean,
        needPaging3Enable: Boolean
) =
        if (needPaging3Enable) {


            """
package ${packageName}
import com.afanticar.base.ui.BaseMvvmFragment
import androidx.paging.PagingData
import ${packageName}.databinding.Fragment${fragmentClass}Binding
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ${fragmentClass}Fragment : BaseMvvmFragment<Fragment${fragmentClass}Binding,${fragmentClass}FragmentViewModel>(R.layout.${layoutName}) {


    override fun initView() {
    }

    @InternalCoroutinesApi
    override fun initData() {
        mBinding.${beanName.toLowerCase()}Adapter = ${beanName}Adapter(mActivity.applicationContext)
           lifecycleScope.launch {
            mBinding?.viewModel?.getData()?.collect(object : FlowCollector<PagingData<${beanName}>> {
                    override suspend fun emit(value: PagingData<${beanName}>) {
                        mBinding?.${beanName.toLowerCase()}Adapter?.submitData(value)
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
import com.afanticar.base.ui.BaseMvvmFragment
import ${packageName}.databinding.Fragment${fragmentClass}Binding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ${fragmentClass}Fragment : BaseMvvmFragment<Fragment${fragmentClass}Binding,${fragmentClass}FragmentViewModel>(R.layout.${layoutName}) {


    override fun initView() {
    }

    override fun initData() {
    }

//    override fun initOtherViewModel() {
//    }


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
package other.mvvm.activity.src.app_package

fun mvvmAcitivityKt(
        applicationPackage: String?,
        activityClass: String,
        layoutName: String,
        packageName: String,
        beanName: String,
        viewModelEnable: Boolean,
        needPaging3Enable: Boolean
) = if (needPaging3Enable) {
    """
package ${packageName}
import com.afanticar.base.ui.BaseMvvmActivity
import androidx.paging.PagingData
import ${packageName}.databinding.Activity${activityClass}Binding
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ${activityClass}Activity : BaseMvvmActivity<Activity${activityClass}Binding,${activityClass}ActivityViewModel>(R.layout.${layoutName}) {


    override fun initView() {
    }

    override fun initData() {
           lifecycleScope.launch {
            mBinding?.viewModel?.getData()?.collect(object : FlowCollector<PagingData<${beanName}>> {
                    override suspend fun emit(value: PagingData<${beanName}>) {
//                        binding?.${beanName.toLowerCase()}Adapter?.submitData(value)
                    }
                })
        }
    }

    override fun initOtherViewModel() {
    }
}
"""
} else {
    if (viewModelEnable) {
        """
package ${packageName}
import com.afanticar.base.ui.BaseMvvmActivity
import ${packageName}.databinding.Activity${activityClass}Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ${activityClass}Activity : BaseMvvmActivity<Activity${activityClass}Binding,${activityClass}ActivityViewModel>(R.layout.${layoutName}) {


    override fun initView() {
    }

    override fun initData() {
           lifecycleScope.launch {
            binding?.fragmentSecondViewModel?.getData()?.collect(object : FlowCollector<PagingData<${beanName}>> {
                    override suspend fun emit(value: PagingData<${beanName}>) {
//                        binding?.demoAdapter?.submitData(value)
                    }
                })
        }
    }

    override fun initOtherViewModel() {
    }
}
"""
    } else {
        """

package ${packageName}
import com.afanticar.base.ui.BaseActivity

class ${activityClass}Activity : BaseActivity(R.layout.${layoutName}) {
    override fun onEvent() {
      
    }

}
    
   """
    }
}
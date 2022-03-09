package other.mvvm.activity

fun mvvmAcitivityKt(
    basePackageName: String,
    activityClass: String,
    layoutName: String,
    packageName: String,
    beanName: String,
    viewModelEnable: Boolean,
    needPaging3Enable: Boolean
) = if (needPaging3Enable) {
    """
package ${packageName}
import ${basePackageName}.ui.BaseMvvmActivity
import androidx.paging.PagingData
import ${packageName}.databinding.Activity${activityClass}Binding
import com.afanticar.common.ex.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch





@AndroidEntryPoint
class ${activityClass}Activity : BaseMvvmActivity<Activity${activityClass}Binding,${activityClass}ViewModel>(R.layout.${layoutName}) {


    override fun initView() {
    }

    @InternalCoroutinesApi
    override fun initData() {
    
         mBinding.${beanName.toLowerCase()}Adapter = ${beanName}Adapter(mActivity.applicationContext)
    
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
package $packageName

import android.app.Activity
import android.content.Intent
import ${packageName}.util.setOnSingleClickListener
import ${packageName}.databinding.Activity${activityClass}Binding
import com.afanticar.base.ui.BaseMvvmActivity
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class ${activityClass}Activity : BaseMvvmActivity<Activity${activityClass}Binding,${activityClass}ViewModel>(R.layout.${layoutName}) {



  //  val medalId by lazy { intent.getStringExtra("medalId") }

    val m${activityClass}Fragment by lazy {
        ${activityClass}Fragment.newInstance()
    }


    override fun initView(savedInstanceState: Bundle?)  {
         resetImmersionBar(R.color.color_171722,false)
         mBinding.activityBack.setOnSingleClickListener({
            onBackPressed()
        })
        
     
        val translation = supportFragmentManager.beginTransaction()
        translation.replace(R.id.fragment, m${activityClass}Fragment)
        translation.commitNowAllowingStateLoss()

    }

    override fun initData() {

    }

    
    companion object {

        fun launch(
            activity: Activity
        ) {
            activity.startActivity(
                Intent(
                    activity,
                    ${activityClass}Activity::class.java
                ).apply {

                   // putExtra("medalId", medalId)
              
                })
        }
    }
}
"""
    } else {
        """

package ${packageName}
import ${basePackageName}.ui.BaseActivity

class ${activityClass}Activity : BaseActivity(R.layout.${layoutName}) {
    override fun onEvent() {
      
    }
}
    
   """
    }
}
package other.mvvm.fragment

fun mvvmFragmentKt(
    basePackageName: String?,
    fragmentClass: String,
    layoutName: String,
    packageName: String,
    beanName: String,
    viewModelEnable: Boolean,
    needPaging3Enable: Boolean
) = if (viewModelEnable) {
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

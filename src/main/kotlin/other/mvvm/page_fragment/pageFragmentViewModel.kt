package other.mvvm.page_fragment

fun pageFragmentViewModel(
    basePackageName: String,
    packageName: String,
    fragmentClass: String,
    beanName: String,
    needPaging3Enable: Boolean
) = if (needPaging3Enable) {

    """
package ${packageName}

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import ${basePackageName}.viewmodel.BaseViewModel
import com.afanticar.common.net.execute
import com.afanticar.common.net.pageExecute
import com.afanticar.network.bean.AftResult
import com.afanticar.network.bean.CommonPageBean
import kotlinx.coroutines.flow.*

class ${fragmentClass}FragmentViewModel @ViewModelInject 
constructor(application: Application,val repository :${fragmentClass}FragmentRepository): BaseViewModel(application) {

    suspend fun 网络请求(loadStatus: LoadStatusBean):
     Flow<AftResult<CommonPageBean<${beanName}Bean>>> {
        return pageExecute {
            repository.网络请求(current, size)
        }
    }

}
"""

} else {
    """
package ${packageName}

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.afanticar.base.viewmodel.BaseViewModel

class ${fragmentClass}FragmentViewModel @ViewModelInject constructor(
application: Application,repository :${fragmentClass}FragmentRepository):
BaseViewModel(application) {

}
"""
}
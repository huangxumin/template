package other.mvvm.fragment

fun mvvmFragmentViewModel(
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
import androidx.lifecycle.viewModelScope
import ${basePackageName}.viewmodel.BaseViewModel
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class ${fragmentClass}FragmentViewModel @ViewModelInject 
constructor(application: Application,val repository ${fragmentClass}FragmentRepository): BaseViewModel(application) {

    fun getData(): Flow<PagingData<${beanName}>> {
        return repository.getPagingData().cachedIn(viewModelScope)
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
application: Application,repository ${fragmentClass}FragmentRepository):
BaseViewModel(application) {

}
"""
}
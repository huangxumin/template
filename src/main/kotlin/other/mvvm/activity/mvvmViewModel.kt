package other.mvvm.activity.src.app_package

fun mvvmViewModel(
        packageName: String,
        activityClass: String,
        beanName: String,
        needPaging3Enable: Boolean
) = if (needPaging3Enable) {
    """
package ${packageName}

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.afanticar.base.viewmodel.BaseViewModel

class ${activityClass}ActivityViewModel @ViewModelInject 
constructor(application: Application,repository ${activityClass}ActivityRepository): BaseViewModel(application) {

    fun getData(): Flow<PagingData<${beanName}>> {
        return fragmentSecondRepository.getPagingData().cachedIn(viewModelScope)
    }



}
"""
} else {

    """
package ${packageName}

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.afanticar.base.viewmodel.BaseViewModel

class ${activityClass}ActivityViewModel @ViewModelInject 
constructor(application: Application,repository ${activityClass}ActivityRepository): BaseViewModel(application) {


}
"""
}
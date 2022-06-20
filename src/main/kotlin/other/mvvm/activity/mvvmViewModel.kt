package other.mvvm.activity

fun mvvmViewModel(
    basePackageName: String,
    packageName: String,
    activityClass: String,
    beanName: String,
    needPaging3Enable: Boolean
) =

    """
package $packageName

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.afanticar.common.common_page_new_utils.CommonPageViewModel
import com.afanticar.common.common_page_new_utils.bean.CommonPageStatusBean
import com.afanticar.common.common_page_new_utils.bean.LoadStatusBean
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class ${activityClass}ViewModel constructor(application: Application): CommonPageViewModel(application) {


    val repository: ${activityClass}Repository by lazy{${activityClass}Repository()}

    val m${activityClass}Data = MutableLiveData<CommonPageStatusBean<${beanName}Bean>>()

    @InternalCoroutinesApi
    fun get${activityClass}Data(loadStatus: LoadStatusBean) {

        viewModelScope.launch {
         
               val date= repository.get${activityClass}Data(loadStatus)
               m${activityClass}Data.postValue(date)
          
        }

    }
    
}
"""

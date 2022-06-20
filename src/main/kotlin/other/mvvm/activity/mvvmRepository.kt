package other.mvvm.activity.src.app_package

fun mvvmRepository(
        packageName: String,
        activityClass: String,
        beanName: String,
        needPaging3Enable: Boolean,
        pagingSourceName: String
) =
    """
package ${packageName}

import com.afanticar.common.common_page_new_utils.bean.CommonPageStatusBean
import com.afanticar.common.common_page_new_utils.bean.LoadStatusBean
import com.afanticar.common.net.executeReturnCommonBean
import javax.inject.Inject


class ${activityClass}Repository @Inject constructor() {

    suspend fun get${activityClass}Data(loadStatus: LoadStatusBea): CommonPageStatusBean<${beanName}Bean> {

       return executeReturnCommonBean<${beanName}Bean>(loadStatus){
            XService.create().get${activityClass}Data(it.current, it.pageSize,)
       }
    }

}
"""


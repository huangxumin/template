package other.mvvm.activity.src.app_package

fun mvvmRepository(
        packageName: String,
        activityClass: String,
        beanName: String,
        needPaging3Enable: Boolean,
        pagingSourceName: String
) = if (needPaging3Enable) {
    """
package ${packageName}
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ${packageName}.${beanName}
import com.afanticar.network.constants.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ${activityClass}ActivityRepository @Inject constructor() {

    fun getPagingData(): Flow<PagingData<${beanName}>> {
        return Pager(
            config = PagingConfig( pageSize = Constants.PAGE_SIZE),
            pagingSourceFactory = { ${pagingSourceName}() }
        ).flow
    }
}
"""
} else {

    """
package ${packageName}

import com.afanticar.common.common_page_new_utils.bean.CommonPageStatusBean
import com.afanticar.common.net.executeReturnCommonBean
import javax.inject.Inject


class ${activityClass}Repository @Inject constructor() {

    suspend fun get${activityClass}Data(page: Int, size: Int): CommonPageStatusBean<${beanName}Bean> {

       return executeReturnCommonBean<${beanName}Bean>(page,size){
            XService.create().get${activityClass}Data(page, size)
       }
    }

}
"""

}


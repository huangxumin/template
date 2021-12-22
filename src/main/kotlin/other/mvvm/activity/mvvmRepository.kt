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


import com.afanticar.common.bean.CommonPageBean
import com.afanticar.network.bean.BaseResponse
import javax.inject.Inject


class ${activityClass}Repository @Inject constructor() {

    suspend fun getXData(page: Int, size: Int): CommonPageBean<Any> {
        return XService.create().getX(page, size)
    }

}
"""

}


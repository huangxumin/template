package other.mvvm.activity.src.app_package

fun beanPagingSourceKt(
        packageName: String,
        beanName: String,
        pagingSourceName: String,
) =
    """
package ${packageName}
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ${packageName}.${beanName}
import kotlinx.coroutines.GlobalScope
import java.lang.Exception

class ${pagingSourceName}() : PagingSource<Int, ${beanName}>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ${beanName}> {
        try {

            val page = params.key ?: 0
            val pageSize = params.loadSize
            
            //获取数据
            val list = mutableListOf<${beanName}>()

            if(list.isEmpty()&&page==0){
                return LoadResult.Page(mutableListOf(${beanName}().apply { empty=true }), null,null)
            }
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (list.isNotEmpty()) page + 1 else null
            return LoadResult.Page(list, prevKey, nextKey)
        } catch (e: Exception) {
            return LoadResult.Error(e)
//            return LoadResult.Page(mutableListOf(${beanName}().apply { error=true }),null,null )
        }
    }


    override fun getRefreshKey(state: PagingState<Int, ${beanName}>): Int? {
        //高级用法， 暂时不要
        return null
    }


}
"""
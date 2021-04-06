package other.mvvm.activity.src.app_package

fun mvvmAdapterKt(
        basePackageName: String,
        packageName: String,
        beanName: String,
        adapterName: String,
        adapterLayoutName: String,
) =
    """
package ${packageName}

import android.content.Context
import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ${basePackageName}.base_adapter.BaseMvvMCommonAdapter
import ${basePackageName}.base_adapter.ViewHolder
import ${packageName}.${beanName}
import ${packageName}.databinding.${beanName}ItemLayoutBinding
import kotlin.jvm.internal.Intrinsics

@BindingAdapter("${beanName.toLowerCase()}Adapter")
fun bindAdapter(recyclerView: RecyclerView, paramDemoAdapter: ${adapterName}) {
    recyclerView.adapter = paramDemoAdapter
//    recyclerView.adapter = paramDemoAdapter.withLoadStateFooter(LoadMoreAdapter {
//        //这里写重试的回调
//        Log.e("xx", "这里写重试的回调==")
//    })
}


class ${adapterName}(context: Context) :
    BaseMvvMCommonAdapter<${beanName}, ${beanName}ItemLayoutBinding>(
        context,
        R.layout.${adapterLayoutName},
        R.layout.rv_error_layout,
        R.layout.rv_empty_layout,
        diffCallback) {

    class ${adapterName}DiffCallback : DiffUtil.ItemCallback<${beanName}>() {
        override fun areContentsTheSame(param1: ${beanName}, param2:${beanName}): Boolean {
            return Intrinsics.areEqual(param1.xx, param2.xx)
        }

        override fun areItemsTheSame(param1: ${beanName}, param2: ${beanName}): Boolean {
            return Intrinsics.areEqual(param1.xx, param2.xx)
        }
    }

    companion object {
        private val diffCallback: DiffUtil.ItemCallback<${beanName}> =
            ${adapterName}DiffCallback()
    }

    override fun convert(paramViewHolder: ViewHolder, paramT: ${beanName}, position: Int) {
       val binding= DataBindingUtil.getBinding<${beanName}ItemLayoutBinding>(paramViewHolder.itemView)
        binding?.${beanName.toLowerCase()}=paramT
    }


}
"""
package other.mvvm.page_fragment

fun pageAdapterKt(
        basePackageName: String,
        packageName: String,
        beanName: String,
        adapterName: String,
        adapterLayoutName: String,
) =
    """
package ${packageName}

import androidx.recyclerview.widget.DiffUtil
import ${packageName}.R
import ${packageName}.${beanName}Bean
import ${packageName}.databinding.${beanName}ItemLayoutBinding
import com.afanticar.common.common_page_new_utils.adapter.CommonPageAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
        
class ${adapterName}() : CommonPageAdapter<${beanName}Bean,${beanName}ItemLayoutBinding>(
   R.layout.${adapterLayoutName}) {


    init {
        //差异
        setDiffCallback(object : DiffUtil.ItemCallback<${beanName}Bean>() {
            override fun areItemsTheSame(oldItem: ${beanName}Bean, newItem: ${beanName}Bean): Boolean {
                return oldItem?.xx == newItem?.xx
            }

            override fun areContentsTheSame(oldItem: ${beanName}Bean, newItem: ${beanName}Bean): Boolean {
                return oldItem?.xx == newItem?.xx
            }
        })
    }
    
    
    
    
    override fun convert(holder: BaseDataBindingHolder<${beanName}ItemLayoutBinding>, item: ${beanName}Bean) {
        holder.dataBinding?.run {
            item.run {
            //do
            }}
            
    }

}
"""
package other.mvvm.fragment

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.mvvm.activity.res.layout.mvvmAdapterLayoutKt
import other.mvvm.activity.src.app_package.beanKt
import other.mvvm.activity.src.app_package.beanPagingSourceKt
import other.mvvm.activity.src.app_package.mvvmAdapterKt
import other.mvvm.activity.src.app_package.mvvmFragmentRepository


fun RecipeExecutor.mvvmFragmentRecipe(
        moduleData: ModuleTemplateData,
        fragmentClass: String,
        layoutName: String,
        packageName: String,
        basePackageName: String,
        needPaging3Enable: Boolean,
        beanName: String,
        adapterName: String,
        adapterLayoutName: String,
        pagingSourceName: String,
        viewModelEnable: Boolean,
        repositoryEnable: Boolean
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
//    generateManifest(
//            moduleData = moduleData,
//            fragmentClass = "${fragmentClass}Activity",
//            fragmentTitle = fragmentClass,
//            packageName = packageName,
//            isLauncher = false,
//            hasNoActionBar = false,
//            generateActivityTitle = true,
//            requireTheme = false,
//            useMaterial2 = false
//    )

    val mvvmFragment = mvvmFragmentKt(basePackageName,
            fragmentClass, layoutName, packageName,beanName,viewModelEnable,needPaging3Enable)
    // 保存Activity
    save(mvvmFragment, srcOut.resolve("${fragmentClass}Fragment.${ktOrJavaExt}"))
    // 保存xml
    save(mvvmFragmentXml(packageName, fragmentClass,beanName,adapterName,viewModelEnable,needPaging3Enable), resOut.resolve("layout/${layoutName}.xml"))
    if (viewModelEnable||needPaging3Enable) {
        // 保存viewmodel
        save(mvvmFragmentViewModel(basePackageName,packageName, fragmentClass,beanName,needPaging3Enable), srcOut.resolve("${fragmentClass}FragmentViewModel.${ktOrJavaExt}"))

        if (repositoryEnable) {
            // 保存repository
            save(mvvmFragmentRepository(packageName, fragmentClass,beanName,needPaging3Enable,pagingSourceName), srcOut.resolve("${fragmentClass}Repository.${ktOrJavaExt}"))
        }
    }

    if(needPaging3Enable){
        save(beanKt(basePackageName,packageName,beanName), srcOut.resolve("${beanName}.${ktOrJavaExt}"))
        save(mvvmAdapterKt(basePackageName,packageName,beanName,adapterName,adapterLayoutName), srcOut.resolve("${adapterName}.${ktOrJavaExt}"))
        save(mvvmAdapterLayoutKt(packageName,beanName), resOut.resolve("layout/${adapterLayoutName}.xml"))
        save(beanPagingSourceKt(packageName,beanName,pagingSourceName), srcOut.resolve("${pagingSourceName}.${ktOrJavaExt}"))

    }

}
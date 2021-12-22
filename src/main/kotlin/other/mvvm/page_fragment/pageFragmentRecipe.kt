package other.mvvm.page_fragment

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor


fun RecipeExecutor.pageFragmentRecipe(
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

    val mvvmFragment = pageFragmentKt(basePackageName,
            fragmentClass, layoutName, packageName,beanName,viewModelEnable,needPaging3Enable,adapterName)
    // 保存Activity
    save(mvvmFragment, srcOut.resolve("${fragmentClass}Fragment.${ktOrJavaExt}"))
    // 保存xml
    save(pageFragmentXml(packageName, fragmentClass,beanName,adapterName,viewModelEnable,needPaging3Enable), resOut.resolve("layout/${layoutName}.xml"))
    if (viewModelEnable||needPaging3Enable) {
        // 保存viewmodel
        save(pageFragmentViewModel(basePackageName,packageName, fragmentClass,beanName,needPaging3Enable), srcOut.resolve("${fragmentClass}FragmentViewModel.${ktOrJavaExt}"))

        if (repositoryEnable) {
            // 保存repository
            save(pageFragmentRepository(packageName, fragmentClass,beanName,needPaging3Enable,pagingSourceName), srcOut.resolve("${fragmentClass}Repository.${ktOrJavaExt}"))
        }
    }

    if(needPaging3Enable){
        save(pageBeanKt(basePackageName,packageName,beanName), srcOut.resolve("${beanName}Bean.${ktOrJavaExt}"))
        save(pageAdapterKt(basePackageName,packageName,beanName,adapterName,adapterLayoutName), srcOut.resolve("${adapterName}.${ktOrJavaExt}"))
        save(pageAdapterLayoutKt(packageName,beanName), resOut.resolve("layout/${adapterLayoutName}.xml"))
//        save(beanPagingSourceKt(packageName,beanName,pagingSourceName), srcOut.resolve("${pagingSourceName}.${ktOrJavaExt}"))
    }

}
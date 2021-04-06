package other.mvvm.fragment

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API


val mvvmFragmentTemplate
    get() = template {
        revision = 1
        name = "MVVM Fragment"
        description = "适用于MVVM框架的Fragment"
        minApi = MIN_API
        minBuildApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
                WizardUiContext.FragmentGallery,
                WizardUiContext.MenuEntry,
                WizardUiContext.NewProject,
                WizardUiContext.NewModule)

        lateinit var layoutName: StringParameter

        val fragmentClass = stringParameter {
            name = "Fragment Name"
            default = "Test"
            help = "只输入名字，不要包含Fragment"
            constraints = listOf(Constraint.NONEMPTY)
        }

        layoutName = stringParameter {
            name = "Layout Name"
            default = "fragment_test"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { fragmentToLayout(fragmentClass.value.toLowerCase()) }
        }



        val needPaging3 = booleanParameter {
            name = "是否需要创建带paging3的fragmrnt(" +
                    "包括adapter,bean,bottomLoadView,pagingSource,\n " +
                    "remoteMediator)"
            default = false
        }


        val beanName = stringParameter {
            name = "bean Name"
            default = "Test"
            help = "Bean类名"
            visible={needPaging3.value}
            constraints = listOf(Constraint.NONEMPTY)
        }

        val adapterName = stringParameter {
            name = "adapter Name"
            default = ""
            help = "Adapter类名"
            visible={needPaging3.value}
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${extractClassName(beanName.value)}Adapter" }
        }

        val  adapterLayoutName = stringParameter {
            name = "Adapter Layout Name"
            default = ""
            help = "请输入布局的名字"
            visible={needPaging3.value}
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${beanName.value.toLowerCase()}_item_layout" }
        }

        val pagingSourceName = stringParameter {
            name = "PagingSource Name"
            default = ""
            help = "PagingSource类名"
            visible={needPaging3.value}
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${extractClassName(beanName.value)}PagingSource" }
        }


        val needViewModel = booleanParameter {
            name = "是否需要关联创建viewModel(包括repository)"
            default = true
        }


        val needRepository = booleanParameter {
            name = "是否需要关联创建Repository"
            default = true
        }

        val packageName = defaultPackageNameParameter
        val basePackageName = basePackageNameParameter

        widgets(
                TextFieldWidget(fragmentClass),
                TextFieldWidget(layoutName),
                PackageNameWidget(packageName),
                PackageNameWidget(basePackageName),
                CheckBoxWidget(needPaging3),
                TextFieldWidget(beanName),
                TextFieldWidget(adapterName),
                TextFieldWidget(adapterLayoutName),
                TextFieldWidget(pagingSourceName),
                CheckBoxWidget(needViewModel),
                CheckBoxWidget(needRepository),
        )
//        thumb { File("logo.png") }
        recipe = { data: TemplateData ->
            mvvmFragmentRecipe(
                    data as ModuleTemplateData,
                    fragmentClass.value,
                    layoutName.value,
                    packageName.value,
                    basePackageName.value,
                    needPaging3.value,
                    beanName.value,
                    adapterName.value,
                    adapterLayoutName.value,
                    pagingSourceName.value,
                    needViewModel.value,
                    needRepository.value
            )
        }

    }

val defaultPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = "com.mycompany.myapp"
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }

val basePackageNameParameter
    get() = stringParameter {
        name = "Base Module Package name"
        default = "com.afanticar.base"
    }

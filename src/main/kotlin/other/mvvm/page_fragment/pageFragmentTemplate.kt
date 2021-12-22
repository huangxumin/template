package other.mvvm.page_fragment

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API


val pageFragmentTemplate
    get() = template {
        revision = 1
        name = "MVVMPageFragment"
        description = "适用于MVVM框架的Fragment(分页逻辑封装的fragment)"
        minApi = MIN_API
        minBuildApi = MIN_API

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        lateinit var layoutName: StringParameter

        val fragmentClass = stringParameter {
            name = "Fragment Name(不需要包含Fragment)"
            default = "Test"
            help = "只输入名字，不要包含Fragment"
            constraints = listOf(Constraint.NONEMPTY)
        }

        layoutName = stringParameter {
            name = "Layout Name"
            default = "fragment_test"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { fragmentToLayout(fragmentClass.value) }
        }


        val needPaging3 = booleanParameter {
            name = "是否需要创建Apapter"
            default = true
        }


        val beanName = stringParameter {
            name = "bean Name(不需要包含Bean)"
            default = "Test"
            help = "Bean类名"
            visible = { needPaging3.value }
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { fragmentClass.value }
        }

        val adapterName = stringParameter {
            name = "adapter Name"
            default = ""
            help = "Adapter类名"
            visible = { needPaging3.value }
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${extractClassName(beanName.value)}Adapter" }
            suggest = { "${fragmentClass.value }Adapter" }
        }

        val adapterLayoutName = stringParameter {
            name = "Adapter Layout Name"
            default = ""
            help = "请输入布局的名字"
            visible = { needPaging3.value }
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${classToResource(beanName.value)}_item_layout" }
            suggest = { "${classToResource(fragmentClass.value)}_item_layout" }
        }

        val pagingSourceName = ""

        val needViewModel = booleanParameter {
            name = "是否需要关联创建viewModel(包括repository)"
            default = true
        }


        val needRepository = booleanParameter {
            name = "是否需要关联创建Repository"
            default = true
        }

        val packageName = stringParameter {
            name = "Package name,当前使用的base文件夹是com.afanticar.base"
            visible = { !isNewModule }
            default = "com.afanticar"
            constraints = listOf(Constraint.PACKAGE)
            suggest = { packageName }
        }

        val basePackageName = "com.afanticar.base"


        widgets(
            TextFieldWidget(fragmentClass),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageName),
            CheckBoxWidget(needPaging3),
            TextFieldWidget(beanName),
            TextFieldWidget(adapterName),
            TextFieldWidget(adapterLayoutName),
            CheckBoxWidget(needViewModel),
            CheckBoxWidget(needRepository),
        )
//        thumb { File("logo.png") }
        recipe = { data: TemplateData ->
            pageFragmentRecipe(
                data as ModuleTemplateData,
                fragmentClass.value,
                layoutName.value,
                packageName.value,
                basePackageName,
                needPaging3.value,
                beanName.value,
                adapterName.value,
                adapterLayoutName.value,
                pagingSourceName,
                needViewModel.value,
                needRepository.value
            )
        }

    }



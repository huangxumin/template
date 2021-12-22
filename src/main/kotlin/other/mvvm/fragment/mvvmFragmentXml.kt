package other.mvvm.fragment

fun mvvmFragmentXml(
        packageName: String,
        activityClass: String,
        beanName: String,
        adapterName: String,
        viewModelEnable: Boolean,
        needPaging3Enable: Boolean
) = if (needPaging3Enable) {

    """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
 xmlns:app="http://schemas.android.com/apk/res-auto"
>

    <data>
        <variable
            name="${beanName.toLowerCase()}_adapter"
            type="${packageName}.${adapterName}" />


        <variable
            name="viewModel"
            type="${packageName}.${activityClass}FragmentViewModel" />
    </data>
    
<androidx.constraintlayout.widget.ConstraintLayout 
    android:layout_width="match_parent"
    android:layout_height="match_parent">
        <com.scwang.smart.refresh.layout.SmartRefreshLayout
            android:id="@+id/refresh_layout"
            android:layout_marginTop="16dp"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

        <com.scwang.smart.refresh.header.ClassicsHeader
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
            
           <androidx.recyclerview.widget.RecyclerView
            app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:${beanName.toLowerCase()}Adapter="@{${beanName.toLowerCase()}_adapter}"/>
        </com.scwang.smart.refresh.layout.SmartRefreshLayout>

    
</androidx.constraintlayout.widget.ConstraintLayout>
</layout>
"""



} else {
    if (viewModelEnable) {
        """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <variable
            name="viewModel"
            type="${packageName}.${activityClass}FragmentViewModel" />
    </data>
    
    <androidx.constraintlayout.widget.ConstraintLayout 
    android:layout_width="match_parent"
    android:layout_height="match_parent">
</androidx.constraintlayout.widget.ConstraintLayout>
</layout>
"""
    } else {
        """
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="${packageName}.${activityClass}Fragment">
</androidx.constraintlayout.widget.ConstraintLayout>
        """
    }
}
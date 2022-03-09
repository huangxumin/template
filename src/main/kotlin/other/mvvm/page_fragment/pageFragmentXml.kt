package other.mvvm.page_fragment

fun pageFragmentXml(
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

    </data>
    
<androidx.constraintlayout.widget.ConstraintLayout 
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
        <include
          android:id="@+id/loading"
          layout="@layout/my_progress_loading" />
    
         <com.scwang.smart.refresh.layout.SmartRefreshLayout
             android:id="@+id/refresh_layout"
             android:layout_width="match_parent"
             android:layout_height="match_parent"
             android:background="@null">
                    
         <com.afanticar.common.widget.CustomSmartHeadView
             android:layout_width="match_parent"
             android:layout_height="wrap_content" />
            
            <androidx.recyclerview.widget.RecyclerView
                android:id="@+id/recyclerview"
                android:layout_height="match_parent"
                android:layout_width="match_parent" />
                                
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

    </data>
    
    <androidx.constraintlayout.widget.ConstraintLayout 
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
            <include
            android:id="@+id/loading"
            layout="@layout/common_my_progress_loading" />
    
    
      <com.scwang.smart.refresh.layout.SmartRefreshLayout
            android:id="@+id/refresh_layout"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <com.afanticar.common.widget.CustomSmartHeadView
                android:layout_width="match_parent"
                android:layout_height="wrap_content" />



              <androidx.recyclerview.widget.RecyclerView
                            android:id="@+id/recyclerview"
                            android:layout_width="match_parent"
                            android:layout_height="match_parent" />

        </com.scwang.smart.refresh.layout.SmartRefreshLayout>

    
    
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
package other.mvvm.activity

fun mvvmActivityXml(
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
            type="${packageName}.${activityClass}ActivityViewModel" />
    </data>
    
<androidx.constraintlayout.widget.ConstraintLayout 
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
           <androidx.recyclerview.widget.RecyclerView
            app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:${beanName.toLowerCase()}Adapter="@{${beanName.toLowerCase()}_adapter}"/>
    
    
</androidx.constraintlayout.widget.ConstraintLayout>
</layout>
"""


} else {
    if (viewModelEnable) {
        """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>

    </data>
    
<androidx.constraintlayout.widget.ConstraintLayout 
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
       <TextView
            android:layout_width="match_parent"
            android:layout_height="@dimen/dp44"
            android:gravity="center"
            android:text="xxx"
            android:textColor="#ffffffff"
            android:textSize="18sp"
            app:layout_constraintTop_toTopOf="parent" />


        <LinearLayout
            android:id="@+id/activity_back"
            android:layout_width="wrap_content"
            android:layout_height="44dp"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent">

            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:layout_marginStart="16dp"
                android:layout_marginEnd="16dp"
                android:src="@mipmap/activity_back" />
        </LinearLayout>
        
        <FrameLayout
            android:id="@+id/fragment"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/activity_back" />

    
</androidx.constraintlayout.widget.ConstraintLayout>

</layout>
"""
    } else {

        """
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="${packageName}.${activityClass}Activity">
</androidx.constraintlayout.widget.ConstraintLayout>
        
    """
    }
}
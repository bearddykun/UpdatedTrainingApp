package com.example.updatedtrainingapp.fragments.exerciseChoice

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.ThisApplication
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject
import java.util.*

class ViewPagerAdapter(fm: FragmentManager, private val list: List<ExerciseObject>) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val array = ThisApplication.instance.resources.getStringArray(R.array.muscle_groups)

    override fun getItem(position: Int): Fragment {
        val filteredList = mutableListOf<ExerciseObject>()
        for (o in list) {
            if (o.exerciseGroup.toLowerCase(Locale.ROOT) == array[position].toLowerCase(Locale.ROOT))
            filteredList.add(o)
        }
        return InnerFragment.newInstance(filteredList)
    }

    override fun getCount(): Int {
        return array.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return array[position]
    }
}
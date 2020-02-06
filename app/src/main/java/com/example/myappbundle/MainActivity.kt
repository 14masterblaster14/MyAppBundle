package com.example.myappbundle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * Refer :
 *
 *  https://developer.android.com/studio/build/configure-apk-splits
 *  https://www.raywenderlich.com/9043-android-app-bundles-getting-started
 *  https://medium.com/better-programming/a-practical-guide-to-android-app-bundle-for-beginners-7e8d93831828
 *  https://medium.com/mindorks/android-app-bundle-6c65ce8105a1
 *
 *
 */

class MainActivity : AppCompatActivity() {

    private lateinit var numbers: Array<String>
    private var numbersPagerAdapter: NumbersPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        numbers = resources.getStringArray(R.array.numbers)

        setContentView(R.layout.activity_main)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        numbersPagerAdapter = NumbersPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = numbersPagerAdapter
    }

    inner class NumbersPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(index: Int): Fragment {
            return PlaceholderFragment.newInstance(numbers[index])
        }

        override fun getCount(): Int {
            return numbers.size
        }
    }


    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_main, container, false)
            rootView.section_label.text = arguments?.getString(ARG_SECTION_NUMBER)
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private const val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(number: String): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putString(ARG_SECTION_NUMBER, number)
                fragment.arguments = args
                return fragment
            }
        }
    }

}
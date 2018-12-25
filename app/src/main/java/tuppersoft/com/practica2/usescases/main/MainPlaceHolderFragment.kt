package tuppersoft.com.practica2.usescases.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.albums.AlbumFragment
import tuppersoft.com.practica2.usescases.post.PostFragment
import tuppersoft.com.practica2.usescases.users.UsersFragment

open class MainPlaceHolderFragment : Fragment() {
    companion object {
        private const val ARG_SECTION = "section"

        private fun newInstance(section: String, args: Bundle, activityMain: MainActivity): MainPlaceHolderFragment {
            val fragment: MainPlaceHolderFragment = when (section) {
                activityMain.getString(R.string.tittle_post) -> PostFragment()
                activityMain.getString(R.string.tittle_albums) -> AlbumFragment()
                activityMain.getString(R.string.tittle_users) -> UsersFragment()
                else -> PostFragment()
            }
            args.putString(ARG_SECTION, section)
            fragment.arguments = args
            return fragment
        }

        fun replaceFragment(activityMain: MainActivity, section: String, bundle: Bundle = Bundle()) {
            activityMain.supportFragmentManager.beginTransaction()
                .replace(
                    R.id.idFrameLayout,
                    MainPlaceHolderFragment.newInstance(section, bundle, activityMain),
                    section
                )
                .commit()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).onSectionAttached(
            arguments?.getString(ARG_SECTION) ?: context.getString(R.string.tittle_post)
        )
    }

}
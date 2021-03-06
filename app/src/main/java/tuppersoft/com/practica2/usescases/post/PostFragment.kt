package tuppersoft.com.practica2.usescases.post

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_list.view.*
import tuppersoft.com.data.Repository
import tuppersoft.com.data.connection.ResponseCallback
import tuppersoft.com.domain.dbo.DialogData
import tuppersoft.com.domain.dto.Post
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.dialogs.DialogActions
import tuppersoft.com.practica2.extensions.changeVisibility
import tuppersoft.com.practica2.extensions.showDialog
import tuppersoft.com.practica2.usescases.comments.CommentsActivity
import tuppersoft.com.practica2.usescases.global.*
import tuppersoft.com.practica2.usescases.main.MainPlaceHolderFragment

class PostFragment : MainPlaceHolderFragment(), SearchView.OnQueryTextListener, GlobalListener, DialogActions {

    lateinit var rootView: View

    var initList: MutableList<Post> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.post_list, container, false)
        setHasOptionsMenu(true)
        initRecyclerView()
        getPost()
        toScroll()
        rootView.idFbAdd.setOnClickListener {
            this@PostFragment.childFragmentManager.showDialog(
                DialogData(
                    getString(R.string.info),
                    getString(R.string.new_post),
                    getString(R.string.cancel),
                    getString(R.string.accept),
                    NEW_POST
                )
            )
        }
        return rootView
    }

    private fun initRecyclerView() {
        recyclerView = rootView.idListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = PostAdapter(null, this)
    }

    private fun getPost() {

        rootView.idProgressBar.changeVisibility(View.VISIBLE)

        Repository.getPost(object : ResponseCallback<MutableList<Post>> {
            override fun onResponse(response: MutableList<Post>) {
                initList = response
                (recyclerView.idListRecyclerView.adapter as PostAdapter).addItems(initList)
                rootView.idProgressBar.changeVisibility(View.GONE)
            }

            override fun onFailure(t: Throwable) {
                rootView.idProgressBar.changeVisibility(View.GONE)
                this@PostFragment.childFragmentManager.showDialog(
                    DialogData(
                        getString(R.string.failed),
                        getString(R.string.error_conection),
                        getString(R.string.cancel),
                        getString(R.string.retry),
                        ERR_CONECTION
                    )
                )
            }
        })
    }

    private fun newPost() {

        rootView.idProgressBar.changeVisibility(View.VISIBLE)
        recyclerView.changeVisibility(View.GONE)

        Repository.savePost(Post(1, 1, "Dummy", "Dummy"), object : ResponseCallback<Post> {
            override fun onResponse(response: Post) {
                rootView.idProgressBar.changeVisibility(View.GONE)
                recyclerView.changeVisibility(View.VISIBLE)
                this@PostFragment.childFragmentManager.showDialog(
                    DialogData(
                        getString(R.string.great),
                        getString(R.string.post_created_ok),
                        null,
                        getString(R.string.accept)

                    )
                )
            }

            override fun onFailure(t: Throwable) {
                rootView.idProgressBar.changeVisibility(View.GONE)
                recyclerView.changeVisibility(View.VISIBLE)
                this@PostFragment.childFragmentManager.showDialog(
                    DialogData(
                        getString(R.string.failed),
                        getString(R.string.error_conection),
                        getString(R.string.cancel),
                        getString(R.string.retry),
                        ERR_CONECTION_POST
                    )
                )
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_toolbar, menu)

        val idActionSearch = menu.findItem(R.id.idActionSearch).actionView as SearchView
        idActionSearch.queryHint = getString(R.string.hintSearchPeople)
        idActionSearch.setOnQueryTextListener(this)
        idActionSearch.isIconified = false
        idActionSearch.inputType = InputType.TYPE_CLASS_NUMBER

        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        if (newText == "") {
            (recyclerView.idListRecyclerView.adapter as PostAdapter).resetItems(initList)
        } else {
            (recyclerView.idListRecyclerView.adapter as PostAdapter).resetItems(initList.filter { item -> item.userId.toString() == newText } as MutableList<Post>)
        }
        return true
    }

    override fun <T> onClick(item: T) {
        if (item is Post) {
            startComments(item as Post)
        }
    }

    private fun toScroll() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    rootView.idFbAdd.hide()
                } else {
                    rootView.idFbAdd.show()
                }
            }

        })
    }

    private fun startComments(item: Post) {
        val intent = Intent(activity, CommentsActivity::class.java)
        intent.putExtra(POST, item)
        startActivity(intent)
    }


    override fun buttonPositive(requestCode: Int) {
        when (requestCode) {
            ERR_CONECTION -> getPost()
            NEW_POST, ERR_CONECTION_POST -> newPost()
            else -> (requireActivity() as GlobalActivity).buttonPositive(requestCode)
        }
    }

    override fun buttonNegative(requestCode: Int) {
        (requireActivity() as GlobalActivity).buttonPositive(requestCode)
    }

}


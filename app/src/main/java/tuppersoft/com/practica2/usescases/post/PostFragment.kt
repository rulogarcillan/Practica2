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
import tuppersoft.com.domain.dto.Post
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.dialogs.DialogActions
import tuppersoft.com.practica2.extensions.changeVisibility
import tuppersoft.com.practica2.extensions.log
import tuppersoft.com.practica2.extensions.showDialog
import tuppersoft.com.practica2.usescases.comments.CommentsActivity
import tuppersoft.com.practica2.usescases.global.GlobalListener
import tuppersoft.com.practica2.usescases.global.POST
import tuppersoft.com.practica2.usescases.main.MainPlaceHolderFragment


class PostFragment : MainPlaceHolderFragment(), SearchView.OnQueryTextListener, GlobalListener, DialogActions {

    override fun buttonPositive(requestCode: Int) {
        "positive2".log()
    }

    override fun buttonNegative(requestCode: Int) {
        "negative2".log()
    }


    var initList: MutableList<Post> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.post_list, container, false)
        setHasOptionsMenu(true)
        initRecyclerView(rootView)
        getPost(rootView)
        toScroll(rootView)

        rootView.idFbAdd.setOnClickListener { showDialog() }

        return rootView
    }

    private fun showDialog() {
        // this@PostFragment.childFragmentManager.showPositiveDialog()
        this@PostFragment.childFragmentManager.showDialog()
    }

    private fun initRecyclerView(rootView: View) {
        recyclerView = rootView.idListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = PostAdapter(null, this)
    }

    private fun getPost(view: View) {

        view.idProgressBar.changeVisibility(View.VISIBLE)

        Repository.getPost(object : ResponseCallback<MutableList<Post>> {
            override fun onResponse(response: MutableList<Post>) {
                initList = response
                (recyclerView.idListRecyclerView.adapter as PostAdapter).addItems(initList)
                view.idProgressBar.changeVisibility(View.GONE)
            }

            override fun onFailure(t: Throwable) {
                view.idProgressBar.changeVisibility(View.GONE)
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

    private fun toScroll(view: View) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    view.idFbAdd.hide()
                } else {
                    view.idFbAdd.show()
                }
            }

        })
    }

    private fun startComments(item: Post) {
        val intent = Intent(activity, CommentsActivity::class.java)
        intent.putExtra(POST, item)
        startActivity(intent)
    }

}


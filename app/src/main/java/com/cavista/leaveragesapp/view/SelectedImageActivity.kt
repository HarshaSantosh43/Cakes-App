package com.cavista.leaveragesapp.view

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cavista.leaveragesapp.R
import com.cavista.leaveragesapp.adapter.CommentsListAdapter
import com.cavista.leaveragesapp.databinding.ActivityDescriptionBinding
import com.cavista.leaveragesapp.models.CommentsModel
import com.cavista.leaveragesapp.models.ImageModel
import com.cavista.leaveragesapp.viewmodel.CommentsViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SelectedImageActivity : AppCompatActivity() {

    private var sectionName: String? = ""
    private var imageModel: ImageModel? = null
    private lateinit var commentsViewModel: CommentsViewModel
    private var activityDescriptionBinding: ActivityDescriptionBinding? = null
    private lateinit var editTextComment: TextInputEditText
    private lateinit var textInputLayoutComment: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDescriptionBinding = DataBindingUtil.setContentView(this, R.layout.activity_description)
        activityDescriptionBinding!!.lifecycleOwner = this

        if (intent != null) {

            if (intent.getStringExtra("SectionName") != null) {
                sectionName = intent.getStringExtra("SectionName")
            }
        }

        if (intent != null && intent.getParcelableExtra<Parcelable>("SingleData") != null) {
            imageModel = intent.getParcelableExtra<Parcelable>("SingleData") as ImageModel?
        }
        supportActionBar!!.setTitle(sectionName)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        activityDescriptionBinding!!.title = imageModel!!.title
        activityDescriptionBinding!!.sectionMessage = imageModel!!.title
        activityDescriptionBinding!!.imageUrl = imageModel!!.link
        activityDescriptionBinding!!.bannerUrl = imageModel!!.link

        val recyclerView = activityDescriptionBinding!!.recyclerviewComments
        val adapter = CommentsListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        commentsViewModel = ViewModelProvider(this).get(CommentsViewModel::class.java)

        commentsViewModel.allComments.observe(this, androidx.lifecycle.Observer { comments ->
            comments?.let { adapter.setComments(it) }
        })

        editTextComment = findViewById(R.id.editTextComment)
        textInputLayoutComment = findViewById(R.id.textInputLayoutComment)

        editTextComment.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                if (textInputLayoutComment.error != null) {
                    textInputLayoutComment.error = ""
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            if (TextUtils.isEmpty(editTextComment.text)) {
                textInputLayoutComment.error = "Enter valid comment"
                editTextComment.requestFocus();
            } else {
                hideSoftKeyBoard(this, it)
                val commentModel = CommentsModel(imageModel!!.title, editTextComment.text.toString())
                commentsViewModel.insert(commentModel);
                editTextComment.text = null
            }
        }
    }

    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
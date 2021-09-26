package com.example.filenetworkplayground.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.filenetworkplayground.R
import com.example.filenetworkplayground.base.BaseActivity
import com.example.filenetworkplayground.databinding.ActivityMainBinding
import com.example.filenetworkplayground.ui.adapters.FileAdapter
import com.example.filenetworkplayground.utils.findPostFix
import com.example.filenetworkplayground.utils.shortToast
import java.io.File

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {
  private lateinit var fileAdapter: FileAdapter

  override fun getViewModelClass(): Class<MainVM> = MainVM::class.java

  override fun layoutId(): Int = R.layout.activity_main

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar?.let {
      title = "Your files"
    }
    setupViews()
  }

  override fun onResume() {
    super.onResume()
    //To refresh the list if a user manually deletes the file(s) from some other application and comes back
    if (binding.mainRv.adapter != null && this::fileAdapter.isInitialized) refreshRvItems()
  }

  private fun setupViews() {
    binding.mainFab.setOnClickListener { onFabPressed() }
    setupRecyclerView()
  }

  private fun setupRecyclerView() {
    fileAdapter = FileAdapter(::onRvItemSavePressed, ::onRvItemDeletePressed)
    with(binding.mainRv) {
      adapter = fileAdapter
      setPadding(0, 0, 0, binding.mainFab.top)
    }
    binding.mainRv.smoothScrollToPosition(0)
  }

  private fun onFabPressed() {
    val counter = getExternalFilesDir(null)?.listFiles()?.size
    val file = File(getExternalFilesDir(null)?.path, "MyFile${counter}.txt")
    val filePostFix = counter.findPostFix()
    file.appendText("This is the $counter$filePostFix file by Shubham!")
    refreshRvItems()
    binding.mainRv.smoothScrollToPosition(fileAdapter.itemCount)
  }

  private fun onRvItemSavePressed(
    file: File,
    text: String
  ) {
    file.writeText(text)
    refreshRvItems()
    shortToast(getString(R.string.textSaveSuccessMsg))
  }

  private fun onRvItemDeletePressed(file: File): Boolean {
    val isDeleted = file.delete()
    if (isDeleted) {
      refreshRvItems()
      shortToast(getString(R.string.fileDeletionSuccess))
    } else {
      shortToast(getString(R.string.fileDeletionFailure))
    }
    return isDeleted
  }

  private fun refreshRvItems() = fileAdapter.submitList(getExternalFilesDir(null)?.listFiles()?.toList()?.asReversed())

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.activity_main_menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.activityMainMenu_delete -> {
        if (getExternalFilesDir(null)?.deleteRecursively() == true) {
          shortToast(getString(R.string.allFilesDeletionSuccessful))
          refreshRvItems()
        }
      }
    }
    return super.onOptionsItemSelected(item)
  }
}
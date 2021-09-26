package com.example.filenetworkplayground.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.filenetworkplayground.databinding.FileItemBinding
import com.example.filenetworkplayground.ui.adapters.FileAdapter.FileViewHolder
import java.io.File

class FileAdapter(
  private val onSaveBtnPressed: (file: File, text: String) -> Unit,
  private val onDeleteBtnPressed: (file: File) -> Boolean
) : ListAdapter<File, FileViewHolder>(DiffCallback()) {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): FileViewHolder {
    val binding = FileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return FileViewHolder(binding, onSaveBtnPressed, onDeleteBtnPressed)
  }

  override fun onBindViewHolder(
    holder: FileViewHolder,
    position: Int
  ) {
    val currentItem = getItem(position)
    holder.bind(currentItem)
  }

  class FileViewHolder(
    private val binding: FileItemBinding,
    private val onSaveBtnPressed: (file: File, text: String) -> Unit,
    private val onDeleteBtnPressed: (file: File) -> Boolean
  ) : ViewHolder(binding.root) {
    fun bind(file: File) {
      with(binding) {
        fileItemEt.setText(file.readText())
        fileItemFileName.text = file.name
        fileItemSaveBtn.setOnClickListener {
          if (binding.fileItemEt.text.toString() != file.readText())
          onSaveBtnPressed(file, binding.fileItemEt.text.toString())
        }
        fileItemDeleteBtn.setOnClickListener { onDeleteBtnPressed(file) }
      }
    }
  }

  class DiffCallback : ItemCallback<File>() {
    override fun areItemsTheSame(
      oldItem: File,
      newItem: File
    ) = oldItem.name == newItem.name

    override fun areContentsTheSame(
      oldItem: File,
      newItem: File
    ) = oldItem == newItem
  }
}
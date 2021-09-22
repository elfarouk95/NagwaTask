package com.nagwa.task.Core.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nagwa.task.Core.view.Events.IClickFileListener
import com.nagwa.task.Domain.Model.FileModel
import com.nagwa.task.R
import com.nagwa.task.databinding.FilesLytBinding

class ListFileAdapter : RecyclerView.Adapter<ListVH>() {

    lateinit var context: Context

    var click: IClickFileListener? = null

    var list: List<FileModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListVH {
        context = parent.context
        return ListVH(LayoutInflater.from(context).inflate(R.layout.files_lyt, parent, false))
    }

    override fun onBindViewHolder(holder: ListVH, position: Int) {


        val item = list.get(position)

        if (item.type.equals("VIDEO")) {
            holder.binding.img.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.video
                )
            )
        } else {
            holder.binding.img.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.pdf
                )
            )
        }
        if (item.downloaded) {
            holder.binding.download.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.checked
                )
            )
        } else {
            holder.binding.download.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.down
                )
            )
        }



        holder.binding.name.setText(item.name)


        holder.binding.download.setOnClickListener {

            if (!item.downloaded)
                click?.select(holder.binding, item)
        }


    }

    override fun getItemCount(): Int {
        return list.size;
    }
}


class ListVH(it: View) : RecyclerView.ViewHolder(it) {
    var binding = FilesLytBinding.bind(it)

}
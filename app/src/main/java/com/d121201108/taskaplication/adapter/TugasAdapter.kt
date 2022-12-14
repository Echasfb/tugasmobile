package com.d121201108.taskaplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.d121201108.taskaplication.R
import com.d121201108.taskaplication.fragments.ListFragmentDirections
import com.d121201108.taskaplication.viewmodel.TugasViewModel
import com.d121201108.taskaplication.model.Tugas

class TugasAdapter:RecyclerView.Adapter<TugasAdapter.TugasViewHolder>() {
    private var context: Context? = null
    private var list_tugas = emptyList<Tugas>()
    private lateinit var tugasViewModel : TugasViewModel
    class TugasViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val judul: TextView = itemView.findViewById(R.id.judul_tugas)
        val kategori: TextView = itemView.findViewById(R.id.kategori_tugas)
        val edit: ImageView = itemView.findViewById(R.id.edit)
        val klik:CardView = itemView.findViewById(R.id.klik)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TugasViewHolder {
        context = parent.context
        tugasViewModel = ViewModelProvider(context as FragmentActivity)[TugasViewModel::class.java]
        return TugasViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_task,parent,false))
    }

    override fun onBindViewHolder(holder: TugasViewHolder, position: Int) {
        val currentTugas = list_tugas[position]
        holder.judul.text = currentTugas.judul_tugas
        holder.kategori.text = currentTugas.kategori_tugas

        when(currentTugas.kategori_tugas){
            "Penting Mendesak"->{
                holder.kategori.setTextColor(context!!.resources.getColor(R.color.penting_mendesak))
            }
            "Tidak Penting Mendesak"->{
                holder.kategori.setTextColor(context!!.resources.getColor(R.color.tidak_penting_mendesak))
            }
            "Penting Tidak Mendesak"->{
                holder.kategori.setTextColor(context!!.resources.getColor(R.color.penting_tidak_mendesak))
            }
            "Tidak Penting Tidak Mendesak"->{
                holder.kategori.setTextColor(context!!.resources.getColor(R.color.tidak_penting_tidak_mendesak))
            }
        }
        holder.klik.setOnClickListener{
            holder.itemView.findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(list_tugas[position]))
        }
        holder.edit.setOnClickListener {
            holder.itemView.findNavController().navigate(ListFragmentDirections.actionListFragmentToEditFragment2(list_tugas[position]))
        }

    }

    override fun getItemCount(): Int {
        return list_tugas.size
    }
    fun setData(tugas:List<Tugas>){
        this.list_tugas = tugas
        notifyDataSetChanged()
    }
}
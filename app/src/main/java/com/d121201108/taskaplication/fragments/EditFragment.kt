package com.d121201108.taskaplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.d121201108.taskaplication.R
import com.d121201108.taskaplication.databinding.FragmentEditBinding
import com.d121201108.taskaplication.databinding.FragmentDetailBinding
import com.d121201108.taskaplication.databinding.FragmentTambahTugasBinding
import com.d121201108.taskaplication.model.Tugas
import com.d121201108.taskaplication.viewmodel.TugasViewModel
import kotlinx.coroutines.launch

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding?=null
    private val binding get() = _binding!!
    private lateinit var tugasViewModel : TugasViewModel
    lateinit var kategoriTugas: ArrayAdapter<CharSequence>
    private val args by navArgs<EditFragmentArgs>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEditBinding.inflate(inflater,container,false)
        val view = binding.root

        tugasViewModel = ViewModelProvider(this)[TugasViewModel::class.java]
        kategoriTugas = ArrayAdapter.createFromResource(requireContext(),R.array.kategoriArray, android.R.layout.simple_list_item_1)
        binding.dropdownMenu.setText(args.currentTask.kategori_tugas)
        binding.dropdownMenu.setAdapter(kategoriTugas)
        binding.judulTugas.setText(args.currentTask.judul_tugas)
        binding.isiTugas.setText(args.currentTask.isi_tugas)
        binding.btnAdd.setOnClickListener {
            binding.apply {
                val kategori = dropdownMenu.text.toString()
                val judul = judulTugas.text.toString()
                val isi = isiTugas.text.toString()
                if(kategori.isEmpty()){
                    Toast.makeText(requireContext(),"Kategori Tugas kosong",
                        Toast.LENGTH_SHORT).show()
                    dropdownMenu.requestFocus()
                    return@setOnClickListener
                }
                if(judul.isEmpty()){
                    judulTugas.error = "Tidak boleh kosong"
                    judulTugas.requestFocus()
                    return@setOnClickListener
                }
                if(isi.isEmpty()){
                    isiTugas.error = "Tidak boleh kosong"
                    isiTugas.requestFocus()
                    return@setOnClickListener
                }

                lifecycleScope.launch{
                    val tugas = Tugas(args.currentTask.id,judul,isi,kategori, "Belum Selesai")
                    tugasViewModel.updateTugas(tugas)
                    findNavController().navigate(R.id.action_editFragment2_to_listFragment)
                }
            }
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_editFragment2_to_listFragment)
        }



        return view
    }
}
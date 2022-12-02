package com.example.taskmanager.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {
    lateinit var adapter: TaskAdapter

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var data = App.db.taskDao().getAllTask()
        adapter.addTasks(data)
        binding.recyclerTask.adapter = adapter
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }

        adapter.onLongClickListener = { task ->
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Вы действительно хотите удалить?")
            alertDialog.setNegativeButton(
                "нет",
                DialogInterface.OnClickListener { dialogInterface, i ->
                })
            alertDialog.setPositiveButton(
                "да",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    App.db.taskDao().delete(task)
                    adapter.delete(task)

                })
            alertDialog.create().show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
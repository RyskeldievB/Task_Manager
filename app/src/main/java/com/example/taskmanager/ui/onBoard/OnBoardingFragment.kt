package com.example.taskmanager.ui.onBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.databinding.FragmentOnBoardingBinding
import com.example.taskmanager.local.local.data.Pref
import com.example.taskmanager.model.OnBoard
import com.example.taskmanager.ui.home.adapter.OnBoardingAdapter


class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        val adapter = OnBoardingAdapter {
            pref.saveShowBoarding(true)
            findNavController().navigateUp()
        }

        adapter.add(
            OnBoard(
                "https://20o8nn37v3mzb7unq3fsj61e-wpengine.netdna-ssl.com/wp-content/uploads/2018/11/Tasks-Pillar-Image-003.png",
                "Board 1",
                "1"
            )
        )
        adapter.add(
            OnBoard(
                "https://thumbs.dreamstime.com/b/task-manager-concept-professional-marks-completed-tasks-time-management-flat-vector-illustration-145739856.jpg",
                "Board 2",
                "2"
            )
        )
        adapter.add(
            OnBoard(

                "https://www.braininabox.com.au/wp-content/uploads/2020/10/systems_planner.jpg",
                "Board 3",
                "3"
            )
        )
        binding.viewPager.adapter = adapter
        binding.indicator.setViewPager(binding.viewPager)

    }
    private fun onClick(view: View){
        findNavController().navigateUp()
    }
}
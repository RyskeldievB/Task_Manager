package com.example.taskmanager.ui.profile
import android.R
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.local.local.data.Pref
import com.example.taskmanager.utils.loadImage


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref

    private val getContent: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->
            binding.profileImage.loadImage(imageUri.toString())
            pref.saveImage(imageUri.toString())
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        Glide.with(binding.profileImage).load(pref.getImage()).into(binding.profileImage)
        binding.age.setText(pref.getAge())
        binding.age.addTextChangedListener{
            pref.saveAge(it.toString())
        }

        binding.profileImage.loadImage(pref.getImage().toString())

        binding.name.setText(pref.getName())
        binding.name.addTextChangedListener {
            pref.saveName(binding.name.text.toString())
        }


        binding.profileImage.setOnClickListener{
            getContent.launch("image/*")
        }



        binding.profileImage.setOnLongClickListener {
            pref.deleteImage()
            binding.profileImage.loadImage(pref.getImage().toString())
            false
        }

    }
    private fun initListeners() {
        binding.profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
        }
    }
}
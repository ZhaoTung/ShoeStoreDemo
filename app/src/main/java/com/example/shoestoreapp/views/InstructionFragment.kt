package com.example.shoestoreapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.shoestoreapp.R
import com.example.shoestoreapp.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {
    private lateinit var binding: FragmentInstructionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentInstructionBinding=
            DataBindingUtil.inflate(inflater, R.layout.fragment_instruction, container, false)
        binding.getStarted.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_instructionFragment_to_shoeListFragment)
        }
        return binding.root

    }

}
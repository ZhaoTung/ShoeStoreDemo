package com.example.shoestoreapp.views

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.shoestoreapp.R
import com.example.shoestoreapp.databinding.FragmentShoeListBinding
import com.example.shoestoreapp.databinding.ListViewBinding
import com.example.shoestoreapp.model.Shoe
import com.example.shoestoreapp.viewmodel.ShoeViewModel

class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private lateinit var viewModel: ShoeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                if (menuItem.getItemId() == R.id.loginFragment)
                    findNavController().navigate(R.id.action_shoeListFragment_to_loginFragment)
                return false

            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        viewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]

        viewModel.shoes.observe(viewLifecycleOwner,Observer{item ->
            shoeListView(item)
        })

        binding.lifecycleOwner =viewLifecycleOwner
        binding.floatingActionButton.setOnClickListener{
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment) }

        }

        return binding.root
    }


    private fun shoeListView(item: List<Shoe>?){
        item?.forEach {
            val bindingView= ListViewBinding.inflate(LayoutInflater.from(requireContext()),binding.linearLayoutList,false)
            with(bindingView){
                name.text=it.name
                size.text=it.size
                company.text=it.company
                description.text=it.description
            }
            binding.linearLayoutList.addView(bindingView.root)
        }
    }


}


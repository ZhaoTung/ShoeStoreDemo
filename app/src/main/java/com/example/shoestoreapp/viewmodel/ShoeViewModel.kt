package com.example.shoestoreapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestoreapp.model.Shoe

class ShoeViewModel : ViewModel() {
    private lateinit var _shoeList : MutableList<Shoe>

    // Login button & Signup button
    private val _eventLoginPress = MutableLiveData(false)
    val eventLoginPress: LiveData<Boolean>
        get() = _eventLoginPress

    fun goToWelcomeScreen(){
        _eventLoginPress.value = true
    }

    private var shoesList = mutableListOf<Shoe>()

    private var _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    fun onSave(name: String, company: String, size:String, description: String){
        val newItem =Shoe(name ,company,size,description)
        newItem.let {item ->
            shoesList.add(item)
            _shoes.value = shoesList
        }
    }

}
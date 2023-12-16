package com.john.portfolio.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.john.portfolio.databinding.FragmentSheetBinding
import kotlinx.coroutines.launch

private const val IMAGE = "image"
private const val SPECIES = "species"
private const val GENDER = "gender"
private const val NAME = "name"

class SheetFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dismissBtn.setOnClickListener {
            dismiss()
        }

        getData()
    }

    private fun getData() {
        setFragmentResultListener("requestKey1"){_, bundle ->
            val image = bundle.getString(IMAGE)!!
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                        Glide
                            .with(binding.imgCharacter.context)
                            .load(image)
                            .into(binding.imgCharacter)
                    }
                }
            }
         setFragmentResultListener("requestKey2"){_, bundle ->
            val name = bundle.getString(NAME)!!
            binding.nameChSheet.text = "Имя: $name"
        }
        setFragmentResultListener("requestKey3"){_, bundle ->
            val species = bundle.getString(SPECIES)!!
            binding.speciesChSheet.text = "Вид: $species"
        }
        setFragmentResultListener("requestKey4"){_, bundle ->
            val gender = bundle.getString(GENDER)!!
            binding.genderChSheet.text = "Гендер: $gender"
        }
    }
}
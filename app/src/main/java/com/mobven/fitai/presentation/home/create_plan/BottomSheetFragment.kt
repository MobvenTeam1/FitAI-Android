package com.mobven.fitai.presentation.home.create_plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mobven.fitai.R

class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)

        val closeButton: ImageView = view.findViewById(R.id.closeButton)
        val addTraining: CardView = view.findViewById(R.id.addTrainingPlanCard)
        val addNutrition: CardView = view.findViewById(R.id.addNutritionPlanCard)

        addTraining.setOnClickListener {
            findNavController().navigate(R.id.action_bottomSheetFragment_to_trainingFragment)
        }

        addNutrition.setOnClickListener {
            findNavController().navigate(R.id.action_bottomSheetFragment_to_nutritionFragment)
        }

        closeButton.setOnClickListener {
            dismiss()
        }

        return view
    }
}
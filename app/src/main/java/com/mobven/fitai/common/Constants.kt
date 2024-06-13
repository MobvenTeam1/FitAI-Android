package com.mobven.fitai.common

import com.mobven.fitai.R
import com.mobven.fitai.presentation.home.adapter.CategoryItem
import com.mobven.fitai.util.enums.CategoryType

object Constants {

    const val BASE_URL = "https://talent.mobven.com:5041/"

    const val TIMEOUT = 60L

    const val DATABASE_NAME = "fit_ai_database.db"

    const val PREF_NAME = "fit_ai_pref"

    val trainingCategoryList =
        listOf(
            CategoryItem(
                name = "Fitness",
                image = R.drawable.pilates_woman,
                categoryType = CategoryType.TRAINING,
            ),
            CategoryItem(
                name = "HIIT",
                image = R.drawable.hiit_woman,
                categoryType = CategoryType.TRAINING,
            ),
            CategoryItem(
                name = "YOGA",
                image = R.drawable.yoga_man,
                categoryType = CategoryType.TRAINING,
            ),
            CategoryItem(
                name = "Pilates",
                image = R.drawable.yoga_woman,
                categoryType = CategoryType.TRAINING,
            )
        )

}
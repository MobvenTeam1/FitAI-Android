package com.mobven.fitai.common

import android.content.Context
import com.mobven.fitai.common.Constants.PREF_NAME

object SharedPreferencesHelper {

    // First Launch
    fun saveIsFirstLaunch(context: Context, isFirstEntrance: Boolean) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("isFirstEntrance", isFirstEntrance)
            apply()
        }
    }

    fun getIsFirstLaunch(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean("isFirstEntrance", false)
    }

    // User Auth Key
    fun saveUserAuthKey(context: Context, userAuthKey: String) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("userAuthKey", userAuthKey)
            apply()
        }
    }

    fun getUserAuthKey(context: Context): String? {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString("userAuthKey", "")
    }

    fun deleteUserAuthKey(context: Context) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("userAuthKey", null)
            apply()
        }
    }

    // Nutrition Plan
    fun saveNutritionPlan(context: Context, isNutritionPlanAdded: Boolean) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("nutritionPlan", isNutritionPlanAdded)
            apply()
        }
    }

    fun getNutritionPlan(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean("nutritionPlan", false)
    }

    // Exercise Plan
    fun saveExercisePlan(context: Context, isExercisePlanAdded: Boolean) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("exercisePlan", isExercisePlanAdded)
            apply()
        }
    }

    fun getExercisePlan(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean("exercisePlan", false)
    }


}
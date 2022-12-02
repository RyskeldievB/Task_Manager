package com.example.taskmanager.local.local.data
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import java.net.URL

class Pref(private val context: Context) {
    private val pref: SharedPreferences = context.getSharedPreferences("pref_name", MODE_PRIVATE)

    fun isBoardingShow(): Boolean {
        return  pref.getBoolean(BOARDING_SHOW, false)
    }

    fun saveShowBoarding(isShow: Boolean){
        pref.edit().putBoolean(BOARDING_SHOW,isShow).apply()
    }

    fun saveName(name: String){
        pref.edit().putString(NAME_PROFILE, name).apply()
    }

    fun getName(): String? {
        return pref.getString(NAME_PROFILE,"")
    }

    fun saveAge (age : String) {
        pref.edit().putString(AGE_PROFILE, age).apply()
    }

    fun getAge():String?{
        return pref.getString(AGE_PROFILE, "")
    }


    fun saveImage(url: String){
        pref.edit().putString(IMAGE_PROFILE,url).apply()
    }

    fun deleteImage(){
        pref.edit().remove(IMAGE_PROFILE).apply()
    }

    fun getImage(): String? {
        return pref.getString(IMAGE_PROFILE,"")
    }
    companion object{
        private const val BOARDING_SHOW = "onboarding.show"
        private const val NAME_PROFILE = "profile.name"
        private const val IMAGE_PROFILE ="profile.image"
        private const val AGE_PROFILE ="profile_age"
    }
}
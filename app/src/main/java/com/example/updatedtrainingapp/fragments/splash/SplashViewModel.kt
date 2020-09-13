package com.example.updatedtrainingapp.fragments.splash

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.updatedtrainingapp.R
import com.example.updatedtrainingapp.application.MySharedPreferences
import com.example.updatedtrainingapp.dataBase.Constants.Companion.ABS_GROUP
import com.example.updatedtrainingapp.dataBase.Constants.Companion.BACK_GROUP
import com.example.updatedtrainingapp.dataBase.Constants.Companion.BICEPS_GROUP
import com.example.updatedtrainingapp.dataBase.Constants.Companion.CHEST_GROUP
import com.example.updatedtrainingapp.dataBase.Constants.Companion.LEGS_GROUP
import com.example.updatedtrainingapp.dataBase.Constants.Companion.SHOULDERS_GROUP
import com.example.updatedtrainingapp.dataBase.Constants.Companion.TRICEPS_GROUP
import com.example.updatedtrainingapp.dataBase.dbViewModels.ExerciseDBViewModel
import com.example.updatedtrainingapp.dataBase.objects.ExerciseObject

class SplashViewModel @ViewModelInject constructor(
    private val exerciseDBViewModel: ExerciseDBViewModel
) : ViewModel() {

    fun databaseLoading() {
        if (!MySharedPreferences.isInside(SplashFragment::class.java.simpleName)) {
            putInDB()

            MySharedPreferences.saveString(
                SplashFragment::class.java.simpleName,
                SplashFragment::class.java.simpleName
            )
            Log.i("Inserted", "inserted")
        } else {
            Log.i("Inserted", "not")
        }
    }

    private fun putInDB() {
        insertExercise(R.mipmap.burbell_incline_bench, "Barbell Incline Bench Press", CHEST_GROUP);
        insertExercise(R.mipmap.bench_press, "Bench Press", "CHEST");
        insertExercise(R.mipmap.decline_bench_press, "Decline Bench Press", CHEST_GROUP);
        insertExercise(
            R.mipmap.decline_dumbbell_bench_press,
            "Decline Dumbbell Bench Press",
            CHEST_GROUP
        );
        insertExercise(R.mipmap.dumbbell_bench_press, "Dumbbell Bench Press", CHEST_GROUP);
        insertExercise(R.mipmap.dumbbell_flyes, "Dumbbell Flyes", CHEST_GROUP);
        insertExercise(
            R.mipmap.incline_dumbbell_bench_press,
            "Incline Dumbbell Bench Press",
            CHEST_GROUP
        );
        insertExercise(R.mipmap.incline_dumbbell_flyes, "Incline Dumbbell Flyes", CHEST_GROUP);
        insertExercise(R.mipmap.low_cable_crossover, "Low Cable Crossover", CHEST_GROUP);
        insertExercise(R.mipmap.machine_bench_press, "Machine Bench Press", CHEST_GROUP);
        insertExercise(R.mipmap.macine_flye, "Machine Fly", CHEST_GROUP);
        insertExercise(R.mipmap.bent_over_barbell_row, "Bent Over Barbell Row", BACK_GROUP);
        insertExercise(R.mipmap.chin_up, "Chin-up", BACK_GROUP)
        insertExercise(
            R.mipmap.close_grip_front_lat_pulldown,
            "Close Grip Front Lat Pulldown",
            BACK_GROUP
        );
        insertExercise(R.mipmap.lying_t_bar_row, "Lying T-bar Row", BACK_GROUP);
        insertExercise(R.mipmap.one_arm_dumbbell_row, "One Arm Dumbbell Row", BACK_GROUP);
        insertExercise(
            R.mipmap.reverse_grip_bent_over_rows,
            "Reverse Grip Bent-over Rows",
            BACK_GROUP
        );
        insertExercise(R.mipmap.reverse_mashine_flyes, "Reverse Machine Flyes", BACK_GROUP);
        insertExercise(R.mipmap.seated_cable_row, "Seated Cable Row", BACK_GROUP);
        insertExercise(R.mipmap.underhand_cable_pulldown, "Underhand Cable Pulldown", BACK_GROUP);
        insertExercise(R.mipmap.wide_grip_lat_pulldown, "Wide Grip Lat Pulldown", BACK_GROUP);
        insertExercise(R.mipmap.burbell_curl, "Barbell Curl", BICEPS_GROUP);
        insertExercise(R.mipmap.concentration_curls, "Concentration Curls", BICEPS_GROUP);
        insertExercise(R.mipmap.cross_body_hammer_curl, "Cross Body Hammer Curl", BICEPS_GROUP);
        insertExercise(
            R.mipmap.dumbbell_alternate_bicep_curl,
            "Dumbbell Alternate Bicep Curl",
            BICEPS_GROUP
        );
        insertExercise(R.mipmap.dumbbell_bicep_curl, "Dumbbell Bicep Curl", BICEPS_GROUP);
        insertExercise(R.mipmap.dummbell_priacher_curl, "Dumbbell Preacher Curl", BICEPS_GROUP);
        insertExercise(R.mipmap.overhead_cable_curl, "Overhead Cable Curl", BICEPS_GROUP);
        insertExercise(R.mipmap.preacher_curl, "Preacher Curl", BICEPS_GROUP);
        insertExercise(R.mipmap.seated_dumbbell_curls, "Seated Dumbbell Curls", BICEPS_GROUP);
        insertExercise(
            R.mipmap.standing_biceps_cable_curl,
            "Standing Biceps Cable Curl",
            BICEPS_GROUP
        );
        insertExercise(R.mipmap.standing_reverse_curl, "Standing Reverse Curl", BICEPS_GROUP);
        insertExercise(
            R.mipmap.barbell_shrag_behind_the_back,
            "Barbell Shrug Behind The Back",
            "TRAPS"
        );
        insertExercise(R.mipmap.burbell_shrug, "Barbell Shrug", "TRAPS");
        insertExercise(R.mipmap.dumbbell_shrug, "Dumbbell Shrug", "TRAPS");
        insertExercise(R.mipmap.bench_dips, "Bench Dips", TRICEPS_GROUP);
        insertExercise(
            R.mipmap.cable_one_arm_triceps_extension,
            "Cable One Arm Triceps Extension",
            TRICEPS_GROUP
        );
        insertExercise(
            R.mipmap.cable_rope_overhead_extention,
            "Cable Rope Overhead Extension",
            TRICEPS_GROUP
        );
        insertExercise(
            R.mipmap.close_grip_burbell_press,
            "Close Grip Barbell Press",
            TRICEPS_GROUP
        );
        insertExercise(R.mipmap.dips_triceps, "Dips Triceps", TRICEPS_GROUP);
        insertExercise(
            R.mipmap.dumbbell_one_arm_triceps_extension,
            "Dumbbell One Arm Triceps Extension",
            TRICEPS_GROUP
        );
        insertExercise(R.mipmap.lying_triceps_press, "Lying Triceps Press", TRICEPS_GROUP);
        insertExercise(
            R.mipmap.push_ups_close_triceps_position,
            "Push Ups Close Triceps Position",
            TRICEPS_GROUP
        );
        insertExercise(R.mipmap.standing_triceps_press, "Standing Triceps Press", TRICEPS_GROUP);
        insertExercise(
            R.mipmap.tricep_dumbbell_kickback,
            "Triceps Dumbbell Kickback",
            TRICEPS_GROUP
        );
        insertExercise(R.mipmap.triceps_pushdown, "Triceps Pushdown", TRICEPS_GROUP);
        insertExercise(
            R.mipmap.alternating_deltoid_raise,
            "Alternating Deltoid Raise",
            SHOULDERS_GROUP
        );
        insertExercise(R.mipmap.arnold_dumbbell_press, "Arnold Dumbbell Press", SHOULDERS_GROUP);
        insertExercise(R.mipmap.barbell_shoulder_press, "Barbell Shoulder Press", SHOULDERS_GROUP);
        insertExercise(
            R.mipmap.bent_over_dumbbell_rear_delt_raise,
            "Bent Over Dumbbell Rear Delt Raise",
            SHOULDERS_GROUP
        );
        insertExercise(R.mipmap.burball_rea_delt_rows, "Barbell Rea Delt Rows", SHOULDERS_GROUP);
        insertExercise(R.mipmap.cable_rear_delt_fly, "Cable Rear Delt Fly", SHOULDERS_GROUP);
        insertExercise(
            R.mipmap.dumbbell_shoulder_press,
            "Dumbbell Shoulder Press",
            SHOULDERS_GROUP
        );
        insertExercise(R.mipmap.front_dumbbell_raise, "Front Dumbbell Raise", SHOULDERS_GROUP);
        insertExercise(R.mipmap.reverse_masine_fly, "Reverse Machine Fly", SHOULDERS_GROUP);
        insertExercise(R.mipmap.shoulder_press, "Shoulder Press", SHOULDERS_GROUP);
        insertExercise(R.mipmap.side_lateral_raise, "Side Lateral Raise", SHOULDERS_GROUP);
        insertExercise(R.mipmap.barbell_lunge, "Barbell Lunge", LEGS_GROUP)
        insertExercise(R.mipmap.barbell_squat, "Barbell Squat", LEGS_GROUP)
        insertExercise(R.mipmap.dumbbell_lunges, "Dumbbell Lunges", LEGS_GROUP)
        insertExercise(R.mipmap.dumbbell_step_ups, "Dumbbell Step Ups", LEGS_GROUP)
        insertExercise(R.mipmap.glute_ham_raize, "Glute Ham Raise", LEGS_GROUP)
        insertExercise(R.mipmap.good_morning, "Good Morning", LEGS_GROUP)
        insertExercise(R.mipmap.leg_press, "Leg Press", LEGS_GROUP)
        insertExercise(R.mipmap.mashine_squat, "Machine Squat", LEGS_GROUP)
        insertExercise(R.mipmap.romanian_deadlift, "Romanian Deadlift", LEGS_GROUP)
        insertExercise(
            R.mipmap.romanian_deadlift_from_defict,
            "Romanian Deadlift From Deficit",
            LEGS_GROUP
        )
        insertExercise(R.mipmap.smith_mashine_squat, "Smith Machine Squat", LEGS_GROUP)
        insertExercise(R.mipmap.smith_masine_front_squat, "Smith Machine Front Squat", LEGS_GROUP)
        insertExercise(R.mipmap.squat, "Squat", LEGS_GROUP)
        insertExercise(R.mipmap.leg_extensions, "Leg Extensions", LEGS_GROUP)
        insertExercise(R.mipmap.flat_banch_leg_pull_in, "Flat Bench Leg Pull-in", ABS_GROUP)
        insertExercise(R.mipmap.hanging_pike, "Hanging Pike", ABS_GROUP)
        insertExercise(R.mipmap.janda_sit_up, "Janda Sit-up", ABS_GROUP)
        insertExercise(R.mipmap.knee_hip_raise_on_bars, "Knee-hip Raise On Bars", ABS_GROUP)
        insertExercise(R.mipmap.kneeling_rope_crunch, "Kneeling Rope Crunch", ABS_GROUP)
        insertExercise(R.mipmap.legs_rise_on_bars, "Legs Rise On Bars", ABS_GROUP)
        insertExercise(
            R.mipmap.oblique_crunches_on_the_flor,
            "Oblique Crunches On The Flor",
            ABS_GROUP
        )
        insertExercise(R.mipmap.seateg_leg_tucks, "Seated Leg Tucks", ABS_GROUP)
        insertExercise(R.mipmap.tuck_crunch, "Tuck Crunch", ABS_GROUP)
    }

    private fun insertExercise(image: Int, exerciseName: String, group: String) {
        exerciseDBViewModel.insertExercise(
            ExerciseObject(
                null,
                exerciseName = exerciseName,
                exerciseGroup = group,
                exerciseImage = image.toString()
            )
        )
    }
}
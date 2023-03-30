package com.iggos.speedyworkoutapp

import android.content.Context

data class TrainingImage(val image: Int, val desc: String)

class TrainingImagesWithDescription(context: Context) {
    val images = arrayListOf<TrainingImage>(
        TrainingImage(R.drawable.balancing, context.getString(R.string.balancing)),
        TrainingImage(R.drawable.crunches, context.getString(R.string.crunches)),
        TrainingImage(R.drawable.flutter_kicks, context.getString(R.string.flutter_kicks)),
        TrainingImage(R.drawable.frog_jumps, context.getString(R.string.frog_jumps)),
        TrainingImage(R.drawable.jumping_jacks, context.getString(R.string.jumping_jacks)),
        TrainingImage(R.drawable.push_ups, context.getString(R.string.push_ups)),
        TrainingImage(R.drawable.running_in_place, context.getString(R.string.running_in_place)),
        TrainingImage(R.drawable.squats, context.getString(R.string.squats)),
        TrainingImage(R.drawable.stretching, context.getString(R.string.stretching)),
    )
}

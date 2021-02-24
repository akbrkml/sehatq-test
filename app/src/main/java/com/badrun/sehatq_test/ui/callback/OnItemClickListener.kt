package com.badrun.sehatq_test.ui.callback

interface OnItemClickListener<in V> {
    fun onClick(item: V)
}
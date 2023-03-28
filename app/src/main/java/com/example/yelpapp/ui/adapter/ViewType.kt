package com.example.yelpapp.ui.adapter

import com.example.yelpapp.domain.Rating

sealed class ViewType {

    data class RATING(val rating: Rating): ViewType()

    data class USER(val rating: Rating): ViewType()
}
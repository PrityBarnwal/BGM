package com.applaunch.bgm.utils

object Constant {
    object HandlerTime {
        const val splashTime = 2000L
    }

    object Count {
        const val zero = 0
        const val one = 1
        const val two = 2
    }

    object BundleKey {
        const val position = "position"
    }

    object RegexPattern {
        const val AccessCodePattern = "[A-Z]{3}-?[0-9]{4}-?[A-Z]{3}"
    }

    object StreamType {
        const val VIDEO = "video"
        const val WEBINAR = "webinar"
        const val ARTICLE = "article"
    }

    object ArticleType{
        const val TEXT ="text"
        const val IMAGE ="image"
        const val PARAGRAPH ="paragraph"

    }
    object WebinarType{
        const val TEXT ="text"
        const val IMAGE ="image"
        const val PARAGRAPH ="paragraph"

    }
    object Pagination{
        const val NETWORK_PAGE_SIZE = 10
        const val INITIAL_LOAD_SIZE = 0
    }
}
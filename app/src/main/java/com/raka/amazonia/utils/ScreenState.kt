package com.raka.amazonia.utils

/**
 * class representing formatted data call results, whether from database or network.
 * instances of this class cannot be modified as they will not be able to notify listeners
 * @param status call status
 * @param data actual data
 * @param extra extra information such as headers which are not part of main data
 * @param code call response code
 * @param message call message
 */
data class ScreenState<out T> private constructor(
    val status: Status,
    val data: T?,
    val extra: Map<String, String> = emptyMap(),
    val code: Int? = null,
    val message: String? = null
) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    /**
     * set of Helper methods to create call results of different states,
     */
    companion object {

        fun <T> success(
            data: T?,
            headers: Map<String, String> = emptyMap(),
            message: String? = null,
            code: Int? = null
        ): ScreenState<T> {
            return ScreenState(Status.SUCCESS, data, headers, code, message)
        }

        fun <T> error(
            message: String? = null,
            code: Int? = null,
            data: T? = null,
            headers: Map<String, String> = emptyMap()
        ): ScreenState<T> {
            return ScreenState(Status.ERROR, data, headers, code, message)
        }

        fun <T> loading(data: T? = null): ScreenState<T> {
            return ScreenState(Status.LOADING, data)
        }
    }
}
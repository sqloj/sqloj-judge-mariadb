package pers.sy.sqloj.util

import java.time.LocalDateTime

data class VResponse<T>(val code: Int, val message: String?, val data: T?, val time: LocalDateTime) {
    companion object {
        private const val OK = 0

        fun <T> ok(): VResponse<T?> {
            return ok(null)
        }

        fun <T> ok(result: T): VResponse<T> {
            return ok(result, null)
        }

        fun <T> ok(result: T, message: String?): VResponse<T> {
            return VResponse(OK, message, result, LocalDateTime.now())
        }

        fun <T> err(code: Int, message: String?, data: T): VResponse<T> {
            return VResponse(code, message, data, LocalDateTime.now())
        }

        fun <T> err(code: Int, message: String?): VResponse<T?> {
            return err(code, message, null)
        }

        fun <T> err(code: Int): VResponse<T?> {
            return err(code, null)
        }
    }

}


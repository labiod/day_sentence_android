package com.bitage.daysentence.factory

import com.bitage.daysentence.dto.SentenceDTO
import io.github.newagewriter.processor.mapper.AbstractMapper
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class MapperKtConverter<T> private constructor(private val clazz: Class<T>) : Converter<ResponseBody, T> where T : Any {

    override fun convert(value: ResponseBody): T? {
        val metadata = value.contentType()?.charset() ?: Charsets.UTF_8
        if (clazz.isAssignableFrom(List::class.java)) {
            return AbstractMapper.fromJsonToArray(clazz, value.byteStream(), metadata) as T
        } else {
            return AbstractMapper.of(clazz)?.jsonToModel(value.byteStream(), metadata)
        }
    }

    private class Factory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *> {
            val clazz =
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
//                // only for gingerbread and newer versions
//                Class.forName(type.typeName)
//            } else {
                SentenceDTO::class.java
//            }
            return MapperKtConverter(clazz)
        }
    }

    companion object {
        fun createFactory(): Converter.Factory {
            return MapperKtConverter.Factory()
        }
    }
}
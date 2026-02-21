package com.procamera.logic

import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream

class SidecarMetadataWriter {

    fun saveMetadata(
        videoFile: File,
        iso: Int,
        shutterSpeed: Long,
        fps: Int,
        resolution: String
    ): String {
        val metadataFile = File(videoFile.parent, "${videoFile.nameWithoutExtension}_metadata.json")
        val json = JSONObject().apply {
            put("filename", videoFile.name)
            put("timestamp", System.currentTimeMillis())
            put("iso", iso)
            put("shutter_speed_ns", shutterSpeed)
            val shutterVal = if (shutterSpeed > 0) "${1_000_000_000.0 / shutterSpeed}s" else "Auto"
            put("shutter_speed_formatted", shutterVal)
            put("fps", fps)
            put("resolution", resolution)
        }

        val jsonString = json.toString(4)
        try {
            FileOutputStream(metadataFile).use { fos ->
                fos.write(jsonString.toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return jsonString
    }
}

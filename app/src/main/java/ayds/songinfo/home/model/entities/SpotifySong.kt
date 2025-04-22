package ayds.songinfo.home.model.entities

import java.time.Month
import java.time.Year
import java.time.format.TextStyle
import java.util.Locale

sealed class Song {
    data class SpotifySong(
        val id: String,
        val songName: String,
        val artistName: String,
        val albumName: String,
        val releaseDate: String,
        val releaseDatePrecision: String,
        val spotifyUrl: String,
        val imageUrl: String,
        var isLocallyStored: Boolean = false
    ) : Song(){
        fun parseReleaseDate():String {
            val date:List<String> = releaseDate.split('-')
            var parsedReleasedDate = ""
            when (releaseDatePrecision) {
                "day" -> parsedReleasedDate = "${date[2]}/${date[1]}/${date[0]}"
                "month" -> parsedReleasedDate = "${
                    Month.of(date[1].toInt()).getDisplayName(
                        TextStyle.FULL,
                        Locale.ENGLISH
                    )
                }, ${date[0]}"

                "year" -> parsedReleasedDate = "${date[0]} ${if(Year.of(date[0].toInt()).isLeap)"(leap year)" else "(not a leap year)"}"
            }
            return parsedReleasedDate
        }
    }

    object EmptySong : Song()
}


package tw.ktrssreader.model.item

import java.util.*

sealed class RssStandardItem(
    open val title: String?,
    open val enclosure: Enclosure?,
    open val guid: String?,
    open val pubDate: Date?,
    open val description: String?,
    open val link: String?,
    open val author: String?,
    open val categories: List<Category>,
    open val comments: String?,
    open val source: String?,
)

data class ITunesItem(
    override val title: String?,
    override val enclosure: Enclosure?,
    override val guid: String?,
    override val pubDate: Date?,
    override val description: String?,
    override val link: String?,
    override val author: String?,
    override val categories: List<Category>,
    override val comments: String?,
    override val source: String?,
    val duration: Long?,
    val image: String?,
    val explicit: Boolean?,
    val episode: Int?,
    val season: Int?,
    val episodeType: String?,
    val block: Boolean?,
) : RssStandardItem(
    title,
    enclosure,
    guid,
    pubDate,
    description,
    link,
    author,
    categories,
    comments,
    source,
)

data class GoogleItem(
    override val title: String?,
    override val enclosure: Enclosure?,
    override val guid: String?,
    override val pubDate: Date?,
    override val description: String?,
    override val link: String?,
    override val author: String?,
    override val categories: List<Category>,
    override val comments: String?,
    override val source: String?,
    val explicit: Boolean?,
    val block: Boolean?,
) : RssStandardItem(
    title,
    enclosure,
    guid,
    pubDate,
    description,
    link,
    author,
    categories,
    comments,
    source,
)

data class AutoMixItem(
    override val title: String?,
    override val enclosure: Enclosure?,
    override val guid: String?,
    override val pubDate: Date?,
    override val description: String?,
    override val link: String?,
    override val author: String?,
    override val categories: List<Category>,
    override val comments: String?,
    override val source: String?,
    val duration: Long?,
    val image: String?,
    val explicit: Boolean?,
    val episode: Int?,
    val season: Int?,
    val episodeType: String?,
    val block: Boolean?,
) : RssStandardItem(
    title,
    enclosure,
    guid,
    pubDate,
    description,
    link,
    author,
    categories,
    comments,
    source,
)
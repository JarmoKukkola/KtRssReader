package tw.ktrssreader.model.channel

import tw.ktrssreader.model.item.AutoMixItem
import tw.ktrssreader.model.item.GoogleItem
import tw.ktrssreader.model.item.ITunesItem
import tw.ktrssreader.model.item.RssStandardItem
import java.util.*

sealed class RssStandardChannel(
    open val title: String?,
    open val description: String?,
    open val image: Image?,
    open val language: String?,
    open val categories: List<String>?,
    open val link: String?,
    open val copyright: String?,
    open val managingEditor: String?,
    open val webMaster: String?,
    open val pubDate: Date?,
    open val lastBuildDate: Date?,
    open val generator: String?,
    open val docs: String?,
    open val cloud: Cloud?,
    open val ttl: Int?,
    open val rating: Float?,
    open val textInput: TextInput?,
    open val skipHours: Int?,
    open val skipDays: String?,
    open val items: List<RssStandardItem>,
)

data class ITunesChannel(
    override val title: String?,
    override val description: String?,
    override val image: Image?,
    override val language: String?,
    override val categories: List<String>?,
    override val link: String?,
    override val copyright: String?,
    override val managingEditor: String?,
    override val webMaster: String?,
    override val pubDate: Date?,
    override val lastBuildDate: Date?,
    override val generator: String?,
    override val docs: String?,
    override val cloud: Cloud?,
    override val ttl: Int?,
    override val rating: Float?,
    override val textInput: TextInput?,
    override val skipHours: Int?,
    override val skipDays: String?,
    override val items: List<ITunesItem>,
    val explicit: Boolean?,
    val author: String?,
    val owner: Owner?,
    val type: String?,
    val newFeedUrl: String?,
    val block: Boolean?,
    val complete: Boolean?,
) : RssStandardChannel(
    title,
    description,
    image,
    language,
    categories,
    link,
    copyright,
    managingEditor,
    webMaster,
    pubDate,
    lastBuildDate,
    generator,
    docs,
    cloud,
    ttl,
    rating,
    textInput,
    skipHours,
    skipDays,
    items
)

data class GoogleChannel(
    override val title: String?,
    override val description: String?,
    override val image: Image?,
    override val language: String?,
    override val categories: List<String>?,
    override val link: String?,
    override val copyright: String?,
    override val managingEditor: String?,
    override val webMaster: String?,
    override val pubDate: Date?,
    override val lastBuildDate: Date?,
    override val generator: String?,
    override val docs: String?,
    override val cloud: Cloud?,
    override val ttl: Int?,
    override val rating: Float?,
    override val textInput: TextInput?,
    override val skipHours: Int?,
    override val skipDays: String?,
    override val items: List<GoogleItem>,
    val explicit: Boolean?,
    val author: String?,
    val owner: String?,
    val block: Boolean?,
) : RssStandardChannel(
    title,
    description,
    image,
    language,
    categories,
    link,
    copyright,
    managingEditor,
    webMaster,
    pubDate,
    lastBuildDate,
    generator,
    docs,
    cloud,
    ttl,
    rating,
    textInput,
    skipHours,
    skipDays,
    items
)

data class AutoMixChannel(
    override val title: String?,
    override val description: String?,
    override val image: Image?,
    override val language: String?,
    override val categories: List<String>?,
    override val link: String?,
    override val copyright: String?,
    override val managingEditor: String?,
    override val webMaster: String?,
    override val pubDate: Date?,
    override val lastBuildDate: Date?,
    override val generator: String?,
    override val docs: String?,
    override val cloud: Cloud?,
    override val ttl: Int?,
    override val rating: Float?,
    override val textInput: TextInput?,
    override val skipHours: Int?,
    override val skipDays: String?,
    override val items: List<AutoMixItem>,
    val explicit: Boolean?,
    val author: String?,
    val owner: Owner?,
    val type: String?,
    val newFeedUrl: String?,
    val block: Boolean?,
    val complete: Boolean?,
) : RssStandardChannel(
    title,
    description,
    image,
    language,
    categories,
    link,
    copyright,
    managingEditor,
    webMaster,
    pubDate,
    lastBuildDate,
    generator,
    docs,
    cloud,
    ttl,
    rating,
    textInput,
    skipHours,
    skipDays,
    items
)
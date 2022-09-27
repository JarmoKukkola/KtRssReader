/*
 * Copyright 2020 Feng Hsien Hsu, Siao Syuan Yang, Wei-Qi Wang, Ya-Han Tsai, Yu Hao Wu
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package tw.ktrssreader.parser

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import tw.ktrssreader.kotlin.constant.ParserConst
import tw.ktrssreader.kotlin.constant.ParserConst.CHANNEL
import tw.ktrssreader.kotlin.constant.ParserConst.DESCRIPTION
import tw.ktrssreader.kotlin.constant.ParserConst.ENTRY
import tw.ktrssreader.kotlin.constant.ParserConst.FEED
import tw.ktrssreader.kotlin.constant.ParserConst.GOOGLE_AUTHOR
import tw.ktrssreader.kotlin.constant.ParserConst.GOOGLE_BLOCK
import tw.ktrssreader.kotlin.constant.ParserConst.GOOGLE_CATEGORY
import tw.ktrssreader.kotlin.constant.ParserConst.GOOGLE_DESCRIPTION
import tw.ktrssreader.kotlin.constant.ParserConst.GOOGLE_EMAIL
import tw.ktrssreader.kotlin.constant.ParserConst.GOOGLE_EXPLICIT
import tw.ktrssreader.kotlin.constant.ParserConst.GOOGLE_IMAGE
import tw.ktrssreader.kotlin.constant.ParserConst.GOOGLE_OWNER
import tw.ktrssreader.kotlin.constant.ParserConst.HREF
import tw.ktrssreader.kotlin.constant.ParserConst.ITEM
import tw.ktrssreader.kotlin.constant.ParserConst.LINK
import tw.ktrssreader.kotlin.constant.ParserConst.PUBLISHED
import tw.ktrssreader.kotlin.constant.ParserConst.TITLE
import tw.ktrssreader.kotlin.model.channel.GoogleChannelData
import tw.ktrssreader.kotlin.model.channel.Image
import tw.ktrssreader.kotlin.model.channel.Owner
import tw.ktrssreader.kotlin.model.channel.RssStandardChannelData
import tw.ktrssreader.kotlin.model.channel.YoutubeChannelData
import tw.ktrssreader.kotlin.model.item.Category
import tw.ktrssreader.kotlin.model.item.GoogleItemData
import tw.ktrssreader.kotlin.model.item.RssStandardItem
import tw.ktrssreader.kotlin.model.item.YoutubeItemData
import tw.ktrssreader.utils.logD
import java.io.IOException

class AndroidYoutubeParser :AndroidParserBase<YoutubeChannelData>() {

    override val rootTag = FEED
    override val logTag: String = AndroidYoutubeParser::class.java.simpleName

    override fun parse(xml: String) = parserYoutubeChannel(xml)

    private fun parserYoutubeChannel(xml: String): YoutubeChannelData {
        return getXmlParser(xml.run {
            substring(indexOf("<feed"))
        }).readYoutubeChannel()
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun XmlPullParser.readYoutubeChannel(): YoutubeChannelData {
        require(XmlPullParser.START_TAG, null, rootTag)
        logD(logTag, "[readYoutubeChannel]: Reading Youtube channel")
        var description: String? = null
        var title: String? = null
        var link: String? = null
        val items: MutableList<YoutubeItemData> = mutableListOf()
        while (next() != XmlPullParser.END_TAG) {
            if (eventType != XmlPullParser.START_TAG) continue
            when (name) {
                DESCRIPTION -> description = readString(DESCRIPTION)
                TITLE -> title = readString(TITLE)
                LINK -> readAttributes(LINK, listOf(HREF)){attr, value ->
                    link = value
                }
                ENTRY -> {
                    items.add(readItem())
                }
                else -> skip()
            }
        }
        return YoutubeChannelData(
            title = title,
            description = description,
            language = null,
            categories = emptyList(),
            link = link,
            copyright = null,
            managingEditor = null,
            webMaster = null,
            pubDate = null,
            lastBuildDate = null,
            generator = null,
            docs = null,
            cloud = null,
            ttl = null,
            rating = null,
            textInput = null,
            skipHours = null,
            skipDays = null,
            items = items,
            author = null,
            image = null
        )
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun XmlPullParser.readItem(): YoutubeItemData {
        require(XmlPullParser.START_TAG, null, ENTRY)
        logD(logTag, "[readItem]: Reading Youtube item")
        var published: String? = null
        var link: String? = null
        var title: String? = null
        while (next() != XmlPullParser.END_TAG) {
            if (eventType != XmlPullParser.START_TAG) continue

            when (name) {
                PUBLISHED -> published = readString(PUBLISHED)
                TITLE -> title = readString(TITLE)
                LINK -> readAttributes(LINK, listOf(HREF)){attr, value ->
                    link = value
                }
                else -> skip()
            }
        }
        require(XmlPullParser.END_TAG, null, ENTRY)
        return YoutubeItemData(
            title = title,
            enclosure = null,
            guid = null,
            pubDate = null,
            link = link,
            author = null,
            categories = null,
            comments = null,
            source = null,
            published = published,
            description = null
        )
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun XmlPullParser.readCategory(): List<Category> {
        require(XmlPullParser.START_TAG, null, GOOGLE_CATEGORY)
        val categories = mutableListOf<Category>()
        getAttributeValue(null, ParserConst.TEXT)?.let {
            categories.add(Category(name = it, domain = null))
        }
        while (next() != XmlPullParser.END_TAG) {
            if (eventType != XmlPullParser.START_TAG) continue

            when (name) {
                GOOGLE_CATEGORY -> {
                    getAttributeValue(null, ParserConst.TEXT)
                        ?.let { categories.add(Category(name = it, domain = null)) }
                    nextTag()
                }
                else -> skip()
            }
        }
        require(XmlPullParser.END_TAG, null, GOOGLE_CATEGORY)
        logD(logTag, "[readCategory]: categories = $categories")
        return categories
    }
}

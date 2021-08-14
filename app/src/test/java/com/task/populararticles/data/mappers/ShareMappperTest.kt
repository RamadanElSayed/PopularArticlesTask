package com.task.populararticles.data.mappers
import com.google.gson.Gson
import com.task.populararticles.data.model.PopularArticlesBaseResponse
import com.task.populararticles.domain.model.ArticleData
import com.task.populararticles.domain.model.PopularArticleBaseData
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ShareMappperTest{

    private lateinit var article: ArticleData
    private lateinit var mostPopularData: PopularArticleBaseData
    private lateinit var shareMapper: SharedMapper
    @Before
    fun setupTest() {
        article = ArticleData("","U.S.","source","title",
            "type","abstract","nytdsection")
        mostPopularData = PopularArticleBaseData("Copyright (c) 2021 The New York Times Company."
                +
                "  All Rights Reserved.",20,listOf(article),"")
    }

    @Test
    fun toMostPobularData() {
        var jsonString = """{"status":"OK","copyright":"Copyright (c) 2021 The New York Times Company.  All Rights Reserved.","num_results":20,"results":[{"uri":"nyt://article/843f7250-9838-5c56-af37-5ca686d7f5a6","url":"https://www.nytimes.com/2021/01/31/us/trump-election-lie.html","id":100000007563821,"asset_id":100000007563821,"source":"New York Times","published_date":"2021-01-31","updated":"2021-02-03 10:27:47","section":"U.S.","subsection":"","nytdsection":"u.s.","adx_keywords":"Presidential Election of 2020;Rumors and Misinformation;Right-Wing Extremism and Alt-Right;Voter Fraud (Election Fraud);Suits and Litigation (Civil);United States Politics and Government;Voting Machines;Attorneys General;Trump, Donald J;Giuliani, Rudolph W;McConnell, Mitch;Clark, Justin Reilly (1975- );Montgomery, Dennis Lee (1953- );McInerney, Thomas G;Gosar, Paul (1958- );Barr, William P;Kobach, Kris W;Paxton, Ken;Kremer, Amy;Kremer, Kylie Jane;Lindell, Mike;Cipollone, Pat A;Women for America First;Dominion Voting Systems Corp","column":null,"byline":"By Jim Rutenberg, Jo Becker, Eric Lipton, Maggie Haberman, Jonathan Martin, Matthew Rosenberg and Michael S. Schmidt","type":"Article","title":"77 Days: Trump’s Campaign to Subvert the Election","abstract":"Hours after the United States voted, the president declared the election a fraud — a lie that unleashed a movement that would shatter democratic norms and upend the peaceful transfer of power.","des_facet":["Presidential Election of 2020","Rumors and Misinformation","Right-Wing Extremism and Alt-Right","Voter Fraud (Election Fraud)","Suits and Litigation (Civil)","United States Politics and Government","Voting Machines","Attorneys General"],"org_facet":["Women for America First","Dominion Voting Systems Corp"],"per_facet":["Trump, Donald J","Giuliani, Rudolph W","McConnell, Mitch","Clark, Justin Reilly (1975- )","Montgomery, Dennis Lee (1953- )","McInerney, Thomas G","Gosar, Paul (1958- )","Barr, William P","Kobach, Kris W","Paxton, Ken","Kremer, Amy","Kremer, Kylie Jane","Lindell, Mike","Cipollone, Pat A"],"geo_facet":[],"media":[{"type":"image","subtype":"photo","caption":"","copyright":"","approved_for_syndication":1,"media-metadata":[{"url":"https://static01.nyt.com/images/2021/02/01/us/01trump-lie-promo/01trump-lie-02-thumbStandard.jpg","format":"Standard Thumbnail","height":75,"width":75},{"url":"https://static01.nyt.com/images/2021/02/01/us/01trump-lie-promo/01trump-lie-02-mediumThreeByTwo210.jpg","format":"mediumThreeByTwo210","height":140,"width":210},{"url":"https://static01.nyt.com/images/2021/02/01/us/01trump-lie-promo/01trump-lie-02-mediumThreeByTwo440.jpg","format":"mediumThreeByTwo440","height":293,"width":440}]}],"eta_id":0}]} """
        val mostPobularRemoteData = Gson().fromJson(jsonString, PopularArticlesBaseResponse::class.java)
        shareMapper = SharedMapper()
        val result = shareMapper.toPopularArticleBaseData(mostPobularRemoteData)
        Assert.assertEquals("U.S.", result.articleData?.get(0)?.section)

    }

}
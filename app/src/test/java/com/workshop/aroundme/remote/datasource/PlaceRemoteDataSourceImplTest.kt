package com.workshop.aroundme.remote.datasource

import com.workshop.aroundme.remote.model.response.*
import com.workshop.aroundme.remote.service.PlaceService
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class PlaceRemoteDataSourceImplTest {
    private val placeService: PlaceService = mockk()

    private val placeRemoteDataSource = PlaceRemoteDataSourceImpl(placeService)


    @Before
    fun setUp() {
        clearAllMocks()
        MockKAnnotations.init(this)
    }

    @Test
    fun `getFeaturedPlaces when list is empty`() {
        val meta: Meta = mockk()
        val response = Response(listOf(), 1)
        val featuredPlacesResponseDto = FeaturedPlacesResponseDto(meta, response)

        every { placeService.getPlaces() } returns Single.just(featuredPlacesResponseDto)

        placeRemoteDataSource.getFeaturedPlaces()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(listOf())
    }


    @Test
    fun `getFeaturedPlaces when list is not empty`() {
        val meta: Meta = mockk()

        val items = listOf(setPlaceDto())

        val response = Response(items, 1)
        val featuredPlacesResponseDto = FeaturedPlacesResponseDto(meta, response)

        val placeEntities = featuredPlacesResponseDto.response?.items?.map { placeDto ->
            placeDto.toPlaceEntity()
        }

        every { placeService.getPlaces() } returns Single.just(featuredPlacesResponseDto)

        placeRemoteDataSource.getFeaturedPlaces()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(placeEntities)
    }

    @Test
    fun `getFeaturedPlaces when fail`() {
        every { placeService.getPlaces() } returns Single.error(Exception())

        placeRemoteDataSource.getFeaturedPlaces()
            .test()
            .assertNotComplete()
            .assertFailure(Exception::class.java)
    }


    @Test
    fun `getPlaceDetail`() {

        val slug = ""
        val meta: Meta = mockk()

        val detailResponseDto = setDetailResponseDtoValue(slug)
        val placeDetailResponseDto = PlaceDetailResponseDto(meta, detailResponseDto)


        every { placeService.getPlaceDetail(slug) } returns Single.just(placeDetailResponseDto)
        placeRemoteDataSource.getPlaceDetail(slug)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(placeDetailResponseDto.response?.toPlaceDetailEntity())
    }


    private fun setPlaceDto(): PlaceDto {

        val att: Attributes? = mockk()
        val categories: List<Category> = mockk()
        val images: List<Image> = listOf(Image(setImageXValue()))

        return PlaceDto(
            "",
            att,
            categories,
            0,
            0,
            0,
            "",
            false,
            0,
            images,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            "",
            "",
            0,
            "",
            0,
            "",
            0,
            "",
            "",
            0,
            0,
            ""
        )
    }

    private fun setDetailResponseDtoValue(slug: String): DetailResponseDto {

        val att: Attributes? = mockk()
        val images: List<DetailImage> = mockk()
        val comments: List<Comment> = mockk()
        val transit: Transit? = mockk()


        val coverImage = CoverImage(setImageXValue())

        val categories: List<Category> = listOf(Category("", 1, ""))
        val tags: List<Tag> = listOf(Tag(1, ""))

        return DetailResponseDto(
            "",
            att,
            false,
            att,
            categories,
            0,
            0,
            comments,
            coverImage,
            0,
            "",
            false,
            0,
            images,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            "",
            "",
            0,
            listOf(),
            "",
            0,
            "",
            listOf(),
            1,
            "",
            "",
            slug,
            tags,
            listOf(),
            0,
            0,
            transit,
            "",
            ""
        )
    }

    private fun setImageXValue(): ImageX {


        val card = Card("")
        val large = Large("")
        val medium = Medium("")
        val small = Small("")
        val tiny = Tiny("")

        return ImageX(card, large, medium, small, tiny)
    }
}
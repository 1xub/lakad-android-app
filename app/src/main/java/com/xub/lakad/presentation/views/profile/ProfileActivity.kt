package com.xub.lakad.presentation.views.profile

import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.itemanimators.SlideDownAlphaAnimator
import com.xub.lakad.R
import com.xub.lakad.presentation.base.BaseActivity
import com.xub.lakad.presentation.common.libs.AppBarStateChangeListener
import com.xub.lakad.presentation.common.libs.HorizontalSpaceItemDecoration
import com.xub.lakad.presentation.common.utils.Utils
import com.xub.lakad.presentation.views.adapter.ProfileItineraryAdapterItem
import kotlinx.android.synthetic.main.activity_profile.app_bar
import kotlinx.android.synthetic.main.activity_profile.imageView_avatar
import kotlinx.android.synthetic.main.activity_profile.iv_profile_bg
import kotlinx.android.synthetic.main.activity_profile.rv_itinerary
import kotlinx.android.synthetic.main.activity_profile.space
import kotlinx.android.synthetic.main.activity_profile.textView_title
import kotlinx.android.synthetic.main.activity_profile.toolbar
import kotlinx.android.synthetic.main.activity_profile.toolbar_title
import kotlinx.android.synthetic.main.activity_profile.view_container
import kotlinx.android.synthetic.main.activity_profile.view_profile_tint
import org.koin.android.ext.android.inject
import java.util.Locale
import kotlin.math.roundToInt
import kotlin.random.Random


/**
 * just came from library, deal with it :P
 * https://github.com/hearsilent/AmazingAvatar/blob/master/app/src/main/java/hearsilent/amazingavatar/MainActivity.java
 */
class ProfileActivity : BaseActivity<ProfileMvpView, ProfilePresenter>(),
    ProfileMvpView {

    companion object {
        private const val EXPAND_AVATAR_SIZE_DP = 80f
        private const val COLLAPSED_AVATAR_SIZE_DP = 32f
    }

    private val mAvatarPoint = FloatArray(2)
    private val mSpacePoint = FloatArray(2)
    private val mToolbarTextPoint = FloatArray(2)
    private var mTitleTextViewPoint = FloatArray(2)
    private var mTitleTextSize: Float = 0f
    private var mAppBarStateChangeListener: AppBarStateChangeListener? = null

    private lateinit var profileItineraryAdapterItem: FastItemAdapter<ProfileItineraryAdapterItem>

    private val mPresenter by inject<ProfilePresenter>()

    override fun getPresenter() = mPresenter

    override fun getView() = this

    override val viewRes: Int = R.layout.activity_profile

    override fun initViews(savedInstanceState: Bundle?) {
        mTitleTextSize = textView_title.textSize
        mAppBarStateChangeListener = object : AppBarStateChangeListener() {

            var scrollRange = -1

            override fun onStateChanged(
                appBarLayout: AppBarLayout,
                state: State
            ) {
            }

            override fun onOffsetChanged(state: State, offset: Float) {
                translationView(offset)

                //Initialize the size of the scroll
                if (scrollRange == -1) {
                    scrollRange = app_bar.totalScrollRange
                }

                //Check if the view is collapsed
                if (offset.toInt() == 1) {
                    iv_profile_bg.visibility = View.GONE
                    view_profile_tint.visibility = View.GONE
                } else {
                    iv_profile_bg.visibility = View.VISIBLE
                    view_profile_tint.visibility = View.VISIBLE
                }
            }
        }

        app_bar.addOnOffsetChangedListener(mAppBarStateChangeListener)
        setUpToolbar()

        initRecyclerView()
    }

    private fun initRecyclerView() {
        profileItineraryAdapterItem = FastItemAdapter()

        rv_itinerary.itemAnimator = SlideDownAlphaAnimator()
        rv_itinerary.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_itinerary.addItemDecoration(HorizontalSpaceItemDecoration(30))
        rv_itinerary.adapter = profileItineraryAdapterItem

        initItineraryItems()
    }

    private fun initItineraryItems() {
        profileItineraryAdapterItem.clear()
        for (i in 0..5) {
            profileItineraryAdapterItem.add(ProfileItineraryAdapterItem(
                ProfileItinerary(
                    image1 = R.drawable.image1,
                    image2 = R.drawable.image2,
                    image3 = R.drawable.image3,
                    name = "Itinerary Name",
                    heartCount = Random.nextDouble(1.0, 9.9)
                )
            ))
        }
        profileItineraryAdapterItem.notifyAdapterDataSetChanged()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            resetPoints(false)
        }
    }

    private fun setUpToolbar() {
        app_bar.layoutParams.height = Utils.getDisplayMetrics(this).widthPixels * 9 / 16
        app_bar.requestLayout()

        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun translationView(offset: Float) {
        val newAvatarSize = Utils.convertDpToPixel(
            EXPAND_AVATAR_SIZE_DP - (EXPAND_AVATAR_SIZE_DP - COLLAPSED_AVATAR_SIZE_DP) * offset,
            this
        )
        val expandAvatarSize = Utils.convertDpToPixel(EXPAND_AVATAR_SIZE_DP, this)
        val xAvatarOffset = (mSpacePoint[0] - mAvatarPoint[0] - (expandAvatarSize - newAvatarSize) / 2f) * offset
        // If avatar center in vertical, just half `(expandAvatarSize - newAvatarSize)`
        val yAvatarOffset = (mSpacePoint[1] - mAvatarPoint[1] - (expandAvatarSize - newAvatarSize)) * offset
        imageView_avatar.layoutParams.width = newAvatarSize.roundToInt()
        imageView_avatar.layoutParams.height = newAvatarSize.roundToInt()
        imageView_avatar.translationX = xAvatarOffset
        imageView_avatar.translationY = yAvatarOffset

        val newTextSize = mTitleTextSize - (mTitleTextSize - toolbar_title.textSize) * offset
        val paint = Paint(textView_title.paint)
        paint.textSize = newTextSize
        val newTextWidth = Utils.getTextWidth(paint, textView_title.text.toString())
        paint.textSize = mTitleTextSize
        val originTextWidth = Utils.getTextWidth(paint, textView_title.text.toString())
        // If rtl should move title view to end of view.
        val isRTL =
            TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL ||
                view_container.layoutDirection == View.LAYOUT_DIRECTION_RTL

        val minus0 = if (isRTL) toolbar_title.width else 0
        val minus1 = if (isRTL) textView_title.width else 0
        val minus = if (toolbar_title.width > newTextWidth)
            (originTextWidth - newTextWidth) / 2f
        else
            0f

        val xTitleOffset = ((mToolbarTextPoint[0] + minus0) -
            (mTitleTextViewPoint[0] + minus1) -
            minus) * offset

        val yTitleOffset = (mToolbarTextPoint[1] - mTitleTextViewPoint[1]) * offset
        textView_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize)
        textView_title.translationX = xTitleOffset
        textView_title.translationY = yTitleOffset
        textView_title.gravity = Gravity.CENTER
        textView_title.textAlignment = View.TEXT_ALIGNMENT_CENTER
    }

    private fun resetPoints(isTextChanged: Boolean) {
        val offset = mAppBarStateChangeListener!!.currentOffset

        val newAvatarSize = Utils.convertDpToPixel(
            EXPAND_AVATAR_SIZE_DP - (EXPAND_AVATAR_SIZE_DP - COLLAPSED_AVATAR_SIZE_DP) * offset,
            this
        )
        val expandAvatarSize = Utils.convertDpToPixel(EXPAND_AVATAR_SIZE_DP, this)

        val avatarPoint = IntArray(2)
        imageView_avatar.getLocationOnScreen(avatarPoint)
        mAvatarPoint[0] = avatarPoint[0] - imageView_avatar.translationX -
            (expandAvatarSize - newAvatarSize) / 2f
        // If avatar center in vertical, just half `(expandAvatarSize - newAvatarSize)`
        mAvatarPoint[1] = avatarPoint[1] - imageView_avatar.translationY -
            (expandAvatarSize - newAvatarSize)

        val spacePoint = IntArray(2)
        space.getLocationOnScreen(spacePoint)
        mSpacePoint[0] = spacePoint[0].toFloat()
        mSpacePoint[1] = spacePoint[1].toFloat()

        val toolbarTextPoint = IntArray(2)
        toolbar_title.getLocationOnScreen(toolbarTextPoint)
        mToolbarTextPoint[0] = toolbarTextPoint[0].toFloat()
        mToolbarTextPoint[1] = toolbarTextPoint[1].toFloat()

        val paint = Paint(textView_title.paint)
        val newTextWidth = Utils.getTextWidth(paint, textView_title.text.toString())
        paint.textSize = mTitleTextSize
        val originTextWidth = Utils.getTextWidth(paint, textView_title.text.toString())
        val titleTextViewPoint = IntArray(2)
        textView_title.getLocationOnScreen(titleTextViewPoint)

        val minus = if (toolbar_title.width > newTextWidth)
            (originTextWidth - newTextWidth) / 2f
        else
            0f

        mTitleTextViewPoint[0] = titleTextViewPoint[0] - textView_title.translationX - minus
        mTitleTextViewPoint[1] = titleTextViewPoint[1] - textView_title.translationY

        if (isTextChanged) {
            Handler().post { translationView(offset) }
        }

        textView_title.gravity = Gravity.CENTER
        textView_title.textAlignment = View.TEXT_ALIGNMENT_CENTER
    }
}

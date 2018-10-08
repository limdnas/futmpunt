package ajatsoft.futmondopuntos


import android.R.attr.description
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntro2
import com.github.paolorotolo.appintro.AppIntroFragment
import com.github.paolorotolo.appintro.model.SliderPage


class IntroActivity : AppIntro2() {

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sliderPage1 = SliderPage()
        sliderPage1.title = "Welcome!"
        sliderPage1.description = "This is a demo of the AppIntro library, with a custom background on each slide!"
        sliderPage1.imageDrawable = R.drawable.ic_slide1
        sliderPage1.bgColor = Color.TRANSPARENT
        addSlide(AppIntroFragment.newInstance(sliderPage1))

        val sliderPage2 = SliderPage()
        sliderPage2.title = "Clean App Intros"
        sliderPage2.description = "This library offers developers the ability to add clean app intros at the start of their apps."
        sliderPage2.imageDrawable = R.drawable.ic_slide2
        sliderPage2.bgColor = Color.TRANSPARENT
        addSlide(AppIntroFragment.newInstance(sliderPage2))

        val sliderPage3 = SliderPage()
        sliderPage3.title = "Simple, yet Customizable"
        sliderPage3.description = "The library offers a lot of customization, while keeping it simple for those that like simple."
        sliderPage3.imageDrawable = R.drawable.ic_slide3
        sliderPage3.bgColor = Color.TRANSPARENT
        addSlide(AppIntroFragment.newInstance(sliderPage3))

        val sliderPage4 = SliderPage()
        sliderPage4.title = "Explore"
        sliderPage4.description = "Feel free to explore the rest of the library demo!"
        sliderPage4.imageDrawable = R.drawable.ic_slide4
        sliderPage4.bgColor = Color.TRANSPARENT
        addSlide(AppIntroFragment.newInstance(sliderPage4))

        // Declare a new image view
        val imageView = ImageView(this)

        // Bind a drawable to the imageview
        imageView.setImageResource(R.drawable.ic_sample_bg)

        // Set background color
        imageView.setBackgroundColor(Color.BLACK)

        // Set layout params
        imageView.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        // Bind the background to the intro
        setBackgroundView(imageView)
    }

    override fun onSkipPressed(currentFragment: Fragment) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment) {
        super.onDonePressed(currentFragment)
        finish()
    }
}

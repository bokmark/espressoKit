package zone.bokmark.espressokit

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


/**
 * 检查imageview的drawable id
 */
fun ViewInteraction.checkImageResource(@DrawableRes id: Int) {
    check(ViewAssertions.matches(withDrawableResource(id)))
}

private fun withDrawableResource(
    @DrawableRes id: Int
) = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("ImageView with drawable same as drawable with id $id")
    }

    override fun matchesSafely(view: View): Boolean {
        val context = view.context
        val expectedBitmap = context.getDrawable(id)?.toBitmap()

        return view is ImageView && view.drawable.toBitmap().sameAs(expectedBitmap)
    }
}

